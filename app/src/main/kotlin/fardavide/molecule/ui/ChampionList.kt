@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package fardavide.molecule.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.SortDirection
import fardavide.molecule.domain.Version
import fardavide.molecule.domain.samples
import fardavide.molecule.presentation.ChampionItemModel
import fardavide.molecule.presentation.Lce
import fardavide.molecule.presentation.ListAction
import fardavide.molecule.presentation.ListState
import fardavide.molecule.presentation.ListViewModel
import fardavide.molecule.presentation.toModels
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChampionList() {
    val viewModel = koinViewModel<ListViewModel>()
    val state by viewModel.state.collectAsState()
    ChampionList(state, viewModel::submit)
}

@Composable
private fun ChampionList(state: ListState, submit: (ListAction) -> Unit) {
    Scaffold(
        topBar = {

        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            when (val champions = state.champions) {
                is Lce.Content -> ChampionListItems(champions.value)
                is Lce.Error -> Text("Error")
                is Lce.Loading -> Text("Loading")
            }
        }
    }
}

@Composable
private fun ChampionListItems(models: List<ChampionItemModel>) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(models, key = { it.id }) { model ->
            ChampionItem(model)
        }
    }
}

@Composable
private fun ChampionItem(model: ChampionItemModel) {
    Row {
        Text(model.name)
    }
}

@Preview
@Composable
fun ChampionListPreview() {
    val version = Version.samples.latest()
    val champions = Champion.samples.all()
    ChampionList(
        state = ListState(
            champions = Lce.Content(champions.toModels(version)),
            searchQuery = "",
            selectedVersion = version.value,
            sort = SortBy.Name(SortDirection.Ascending),
            versions = Lce.Content(listOf(version.value))
        ),
        submit = {}
    )
}
