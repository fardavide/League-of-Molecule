package fardavide.molecule.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import arrow.core.Either
import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.DataError
import fardavide.molecule.domain.LoLRepository
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.SortDirection
import fardavide.molecule.domain.Version
import fardavide.molecule.domain.latest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
class ListPresenter(
    private val repository: LoLRepository
) {

    @Composable
    fun models(actions: Flow<ListAction>): ListState {
        var version: Version? by remember { mutableStateOf(null) }
        var query: String by remember { mutableStateOf("") }
        var sort: SortBy by remember { mutableStateOf(SortBy.Name(SortDirection.Ascending)) }

        val versionsState by remember {
            repository.getVersions()
                .onEach { either ->
                    val newVersion = either.getOrNull()?.latest()
                    if (version == null && newVersion != null) {
                        version = newVersion
                    }
                }
                .mapToModels()
        }.collectAsState(initial = Lce.Loading)

        val championsState by remember(version, query, sort) {
            version?.let { v -> repository.getChampions(v, query, sort).mapToModels(v) }
                ?: flowOf(Lce.Loading)
        }.collectAsState(initial = Lce.Loading)

        LaunchedEffect(Unit) {
            actions.collect { action ->
                when (action) {
                    is ListAction.ChangeVersion -> version = Version(action.version)
                    ListAction.ClearSearch -> query = ""
                    is ListAction.Search -> query = action.query
                    is ListAction.Sort -> sort = action.sortBy
                }
            }
        }

        return ListState(
            champions = championsState,
            selectedVersion = version?.value,
            searchQuery = query,
            sort = sort,
            versions = versionsState
        )
    }

    @JvmName("mapToModelsChampion")
    private fun Flow<Either<DataError, List<Champion>>>.mapToModels(version: Version) =
        map { either -> either.map { it.toModels(version) }.toLce() }

    @JvmName("mapToModelsVersion")
    private fun Flow<Either<DataError, List<Version>>>.mapToModels() =
        map { either -> either.map(::toModels).toLce() }

    @JvmName("toModelsVersion")
    private fun toModels(versions: List<Version>): List<String> = versions.take(50).map { it.value }
}
