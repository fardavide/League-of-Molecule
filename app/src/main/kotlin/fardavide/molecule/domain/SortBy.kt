package fardavide.molecule.domain

sealed interface SortBy {

    val direction: SortDirection

    @JvmInline
    value class Attack(override val direction: SortDirection) : SortBy

    @JvmInline
    value class Defence(override val direction: SortDirection) : SortBy

    @JvmInline
    value class Difficulty(override val direction: SortDirection) : SortBy

    @JvmInline
    value class Magic(override val direction: SortDirection) : SortBy

    @JvmInline
    value class Name(override val direction: SortDirection) : SortBy

    companion object
}

fun SortBy.Companion.fromString(string: String, currentSort: SortBy): SortBy = when {
    string.startsWith("Attack") && currentSort is SortBy.Attack -> currentSort.toggleDirection()
    string.startsWith("Attack") -> SortBy.Attack(SortDirection.Ascending)
    string.startsWith("Defence") && currentSort is SortBy.Defence -> currentSort.toggleDirection()
    string.startsWith("Defence") -> SortBy.Defence(SortDirection.Ascending)
    string.startsWith("Difficulty") && currentSort is SortBy.Difficulty -> currentSort.toggleDirection()
    string.startsWith("Difficulty") -> SortBy.Difficulty(SortDirection.Ascending)
    string.startsWith("Magic") && currentSort is SortBy.Magic -> currentSort.toggleDirection()
    string.startsWith("Magic") -> SortBy.Magic(SortDirection.Ascending)
    string.startsWith("Name") && currentSort is SortBy.Name -> currentSort.toggleDirection()
    string.startsWith("Name") -> SortBy.Name(SortDirection.Ascending)
    else -> error("Invalid SortBy string: $string")
}

fun SortBy.toggleDirection(): SortBy = when (this) {
    is SortBy.Attack -> SortBy.Attack(direction.toggle())
    is SortBy.Defence -> SortBy.Defence(direction.toggle())
    is SortBy.Difficulty -> SortBy.Difficulty(direction.toggle())
    is SortBy.Magic -> SortBy.Magic(direction.toggle())
    is SortBy.Name -> SortBy.Name(direction.toggle())
}

fun SortBy.string(): String = when (this) {
    is SortBy.Attack -> "Attack"
    is SortBy.Defence -> "Defence"
    is SortBy.Difficulty -> "Difficulty"
    is SortBy.Magic -> "Magic"
    is SortBy.Name -> "Name"
} + " ${direction.string()}"

enum class SortDirection {
    Ascending,
    Descending
}

fun SortDirection.toggle(): SortDirection = when (this) {
    SortDirection.Ascending -> SortDirection.Descending
    SortDirection.Descending -> SortDirection.Ascending
}

fun SortDirection.string(): String = when (this) {
    SortDirection.Ascending -> "🔼"
    SortDirection.Descending -> "🔽"
}
