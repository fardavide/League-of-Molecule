package fardavide.molecule.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import arrow.core.Either
import fardavide.molecule.domain.LoLRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class LoLPresenter(
    private val repository: LoLRepository
) {

    @Composable
    fun models(actions: Flow<Action>): State {
        var query by remember { mutableStateOf("") }
        var sort: SortBy by remember { mutableStateOf(SortBy.Name(SortDirection.Ascending)) }

        val state by remember(query, sort) {
            // TODO: query and sort
            repository.getAllChampions().map { either ->
                either.fold(
                    ifLeft = { State.Error },
                    ifRight = { State.Content(it) }
                )
            }
        }.collectAsState(initial = State.Loading)

        LaunchedEffect(Unit) {
            actions.collect { action ->
                when (action) {
                    Action.ClearSearch -> query = ""
                    is Action.Search -> query = action.query
                    is Action.Sort -> sort = action.sortBy
                }
            }
        }

        return state
    }
}
