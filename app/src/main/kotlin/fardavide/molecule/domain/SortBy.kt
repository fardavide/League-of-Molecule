package fardavide.molecule.domain

sealed interface SortBy {

    @JvmInline value class Attack(val direction: SortDirection) : SortBy

    @JvmInline value class Defence(val direction: SortDirection) : SortBy

    @JvmInline value class Difficulty(val direction: SortDirection) : SortBy

    @JvmInline value class Magic(val direction: SortDirection) : SortBy

    @JvmInline value class Name(val direction: SortDirection) : SortBy
}

enum class SortDirection {
    Ascending,
    Descending
}
