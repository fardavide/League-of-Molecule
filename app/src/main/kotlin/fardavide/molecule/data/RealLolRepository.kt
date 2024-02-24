package fardavide.molecule.data

import arrow.core.Either
import fardavide.molecule.domain.DataError
import fardavide.molecule.domain.LoLRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory

@Factory
class RealLolRepository(
    private val client: HttpClient
): LoLRepository {

    override fun getAllChampions() = flow { emit(fetchChampions()) }

    private suspend fun fetchChampions() = Either
        .catch { client.get(URL).body<ChampionsResponse>().data.values.toList() }
        .mapLeft { e ->
            e.printStackTrace()
            DataError
        }

    companion object {

        private const val URL = "https://ddragon.leagueoflegends.com/cdn/11.20.1/data/en_US/champion.json"
    }
}

@Factory
fun Client() = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}
