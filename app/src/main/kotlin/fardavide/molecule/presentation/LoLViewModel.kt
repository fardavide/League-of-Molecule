package fardavide.molecule.presentation

import androidx.compose.runtime.Composable
import fardavide.molecule.domain.Champion
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoLViewModel(
    private val presenter: LoLPresenter
) : MoleculeViewModel<Action, State>() {

    @Composable
    override fun models(actions: Flow<Action>): State = presenter.models(actions)
}

sealed interface Action {

    data object ClearSearch : Action
    @JvmInline value class Search(val query: String) : Action
    @JvmInline value class Sort(val sortBy: SortBy) : Action
}

sealed interface State {
    @JvmInline value class Content(val champions: List<Champion>) : State
    data object Error : State
    data object Loading : State
}
