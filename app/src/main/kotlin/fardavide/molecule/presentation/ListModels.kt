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
    val champions: Lce<List<ChampionItemModel>>,
    val selectedVersion: String?,
    val searchQuery: String,
    val sort: SortBy,
    val versions: Lce<List<String>>
)

data class ChampionItemModel(
    val attack: Int,
    val defense: Int,
    val difficulty: Int,
    val id: String,
    val imageUrl: String,
    val magic: Int,
    val name: String,
    val tags: List<String>,
    val title: String
)

@JvmName("toModelsChampion")
fun List<Champion>.toModels(version: Version): List<ChampionItemModel> = map { champion ->
    ChampionItemModel(
        attack = champion.info.attack,
        defense = champion.info.defense,
        difficulty = champion.info.difficulty,
        id = champion.id,
        imageUrl = Urls.image(champion, version),
        magic = champion.info.magic,
        name = champion.name,
        tags = champion.tags,
        title = champion.title
    )
}
