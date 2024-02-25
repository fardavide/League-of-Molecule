package fardavide.molecule.presentation

import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.Urls
import fardavide.molecule.domain.Version

sealed interface ListAction {

    @JvmInline
    value class ChangeVersion(val version: String) : ListAction

    data object ClearSearch : ListAction

    @JvmInline
    value class Search(val query: String) : ListAction

    @JvmInline
    value class Sort(val sortBy: SortBy) : ListAction
}

data class ListState(
    val championsState: Lce<List<ChampionItemModel>>,
    val selectedVersion: String?,
    val searchQuery: String,
    val sort: SortBy,
    val versions: Lce<List<String>>
)

data class ChampionItemModel(
    val id: String,
    val imageUrl: String,
    val name: String,
    val tags: List<String>,
    val title: String
)

@JvmName("toModelsChampion")
fun List<Champion>.toModels(version: Version): List<ChampionItemModel> = map { champion ->
    ChampionItemModel(
        id = champion.id,
        imageUrl = Urls.image(champion, version),
        name = champion.name,
        tags = champion.tags,
        title = champion.title
    )
}
