@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package fardavide.molecule.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.SortDirection
import fardavide.molecule.domain.Version
import fardavide.molecule.domain.fromString
import fardavide.molecule.domain.samples
import fardavide.molecule.domain.string
import fardavide.molecule.presentation.ChampionItemModel
import fardavide.molecule.presentation.Lce
import fardavide.molecule.presentation.ListAction
import fardavide.molecule.presentation.ListState
import fardavide.molecule.presentation.ListViewModel
import fardavide.molecule.presentation.orEmpty
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
            Column {
                CenterAlignedTopAppBar(title = { Text("League of Molecule") })
                SearchField(state.searchQuery, submit)
                Spacer(modifier = Modifier.height(4.dp))
                Options(state.sort, state.versions.orEmpty(), state.selectedVersion, submit)
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.navigationBars)
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            when (val champions = state.champions) {
                is Lce.Content -> ChampionItems(champions.value)
                is Lce.Error -> Error()
                is Lce.Loading -> Loading()
            }
        }
    }
}

@Composable
private fun SearchField(searchQuery: String, submit: (ListAction) -> Unit) {
    var value by remember { mutableStateOf(searchQuery) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = value,
        onValueChange = { text ->
            value = text
            submit(ListAction.Search(text))
        },
        label = { Text("Search") },
        singleLine = true,
        trailingIcon = {
            if (value.isNotEmpty()) {
                TextButton(onClick = {
                    value = ""
                    submit(ListAction.ClearSearch)
                }) {
                    Text("Clear")
                }
            }
        }
    )
}

@Composable
private fun Options(
    sort: SortBy,
    versions: List<String>,
    selectedVersion: String?,
    submit: (ListAction) -> Unit
) {
    var isSortOped by remember { mutableStateOf(false) }
    var isVersionOped by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Sort by", style = MaterialTheme.typography.labelMedium)
        ElevatedAssistChip(
            onClick = { isSortOped = true },
            label = {
                Text(text = sort.string())
                DropdownMenu(expanded = isSortOped, onDismissRequest = { isSortOped = false }) {
                    SortDropdownItem(name = "Name", sort) {
                        isSortOped = false
                        submit(it)
                    }
                    SortDropdownItem(name = "Difficulty", sort) {
                        isSortOped = false
                        submit(it)
                    }
                    SortDropdownItem(name = "Attack", sort) {
                        isSortOped = false
                        submit(it)
                    }
                    SortDropdownItem(name = "Magic", sort) {
                        isSortOped = false
                        submit(it)
                    }
                    SortDropdownItem(name = "Defence", sort) {
                        isSortOped = false
                        submit(it)
                    }
                }
            }
        )
        Text(text = "Version", style = MaterialTheme.typography.labelMedium)
        ElevatedAssistChip(
            onClick = { isVersionOped = true },
            label = {
                Text(text = selectedVersion.orEmpty())
                DropdownMenu(expanded = isVersionOped, onDismissRequest = { isVersionOped = false }) {
                    for (version in versions) {
                        VersionDropdownItem(version, selectedVersion) {
                            isVersionOped = false
                            submit(it)
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun SortDropdownItem(
    name: String,
    currentSort: SortBy,
    submitAndDismiss: (ListAction) -> Unit
) {
    val text = if (currentSort.string().startsWith(name)) "$name (selected)" else name
    DropdownMenuItem(
        onClick = { submitAndDismiss(ListAction.Sort(SortBy.fromString(name, currentSort))) },
        text = { Text(text = text) }
    )
}

@Composable
private fun VersionDropdownItem(
    version: String,
    selectedVersion: String?,
    submit: (ListAction) -> Unit
) {
    val text = if (version == selectedVersion) "$version (selected)" else version
    DropdownMenuItem(
        onClick = { submit(ListAction.ChangeVersion(version)) },
        text = { Text(text = text) }
    )
}

@Composable
private fun ChampionItems(models: List<ChampionItemModel>) {
    val listState = rememberLazyListState()
    LaunchedEffect(models) { listState.animateScrollToItem(0) }
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        state = listState
    ) {
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

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(64.dp))
    }
}

@Composable
private fun Error() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Something went wrong.",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ChampionList_Content_Preview() {
    val version = Version.samples.latest()
    val champions = Champion.samples.all()
    ChampionList(
        state = ListState(
            champions = Lce.Content(champions.toModels(version)),
            searchQuery = "Teemo",
            selectedVersion = version.value,
            sort = SortBy.Name(SortDirection.Ascending),
            versions = Lce.Content(listOf(version.value))
        ),
        submit = {}
    )
}

@Preview
@Composable
fun ChampionList_Error_Preview() {
    ChampionList(
        state = ListState(
            champions = Lce.Error,
            searchQuery = "",
            selectedVersion = null,
            sort = SortBy.Name(SortDirection.Ascending),
            versions = Lce.Loading
        ),
        submit = {}
    )
}

@Preview
@Composable
fun ChampionList_Loading_Preview() {
    ChampionList(
        state = ListState(
            champions = Lce.Loading,
            searchQuery = "",
            selectedVersion = null,
            sort = SortBy.Name(SortDirection.Ascending),
            versions = Lce.Loading
        ),
        submit = {}
    )
}
