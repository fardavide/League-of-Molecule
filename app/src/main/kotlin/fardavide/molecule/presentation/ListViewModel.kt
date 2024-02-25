package fardavide.molecule.presentation

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ListViewModel(
    private val presenter: ListPresenter
) : MoleculeViewModel<ListAction, ListState>() {

    @Composable
    override fun models(actions: Flow<ListAction>): ListState = presenter.models(actions)
}

