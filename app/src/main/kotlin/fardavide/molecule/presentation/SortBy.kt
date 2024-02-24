package fardavide.molecule.presentation

sealed interface SortBy {
    @JvmInline value class Name(val direction: SortDirection) : SortBy
}

enum class SortDirection {
    Ascending,
    Descending
}
