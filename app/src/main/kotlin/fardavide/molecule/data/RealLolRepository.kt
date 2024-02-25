package fardavide.molecule.data

import arrow.core.Either
import arrow.core.right
import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.DataError
import fardavide.molecule.domain.LoLRepository
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.Urls
import fardavide.molecule.domain.Version
import fardavide.molecule.domain.comparator
import fardavide.molecule.domain.matcher
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

@Single
class RealLolRepository(
    private val client: HttpClient
) : LoLRepository {

    private val championsCache: MutableMap<Version, List<Champion>> = mutableMapOf()
    private val versionsCache: MutableList<Version> = mutableListOf()

    override fun getChampions(
        version: Version,
        query: String,
        sortBy: SortBy
    ) = flow {
        val champions = fetchChampions(version).map { list ->
            list
                .filter(Champion.matcher(query))
                .sortedWith(Champion.comparator(sortBy))
        }
        emit(champions)
    }

    override fun getVersions(): Flow<Either<DataError, List<Version>>> = flow {
        emit(fetchVersions())
    }

    private suspend fun fetchChampions(version: Version): Either<DataError, List<Champion>> {
        championsCache[version]?.let { return it.right() }
        return fetch<ChampionsResponse>(Urls.champions(version))
            .map { it.data.values.toList() }
            .onRight { championsCache[version] = it }
    }

    private suspend fun fetchVersions(): Either<DataError, List<Version>> {
        if (versionsCache.isNotEmpty()) return versionsCache.right()
        return fetch<List<String>>(Urls.versions())
            .map { it.map(::Version) }
            .onRight { versionsCache.addAll(it) }
    }

    private suspend inline fun <reified T> fetch(url: String): Either<DataError, T> {
        return Either
            .catch { client.get(url).body<T>() }
            .mapLeft { e ->
                e.printStackTrace()
                DataError
            }
    }
}

@Factory
fun Client() = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}
