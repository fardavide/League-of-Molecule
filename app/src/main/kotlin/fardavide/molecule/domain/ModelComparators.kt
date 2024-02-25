package fardavide.molecule.domain

fun Champion.Companion.comparator(sortBy: SortBy): Comparator<Champion> = Comparator { a, b ->
    when (sortBy) {
        is SortBy.Attack -> when (sortBy.direction) {
            SortDirection.Ascending -> a.info.attack.compareTo(b.info.attack)
            SortDirection.Descending -> b.info.attack.compareTo(a.info.attack)
        }
        is SortBy.Defence -> when (sortBy.direction) {
            SortDirection.Ascending -> a.info.defense.compareTo(b.info.defense)
            SortDirection.Descending -> b.info.defense.compareTo(a.info.defense)
        }
        is SortBy.Difficulty -> when (sortBy.direction) {
            SortDirection.Ascending -> a.info.difficulty.compareTo(b.info.difficulty)
            SortDirection.Descending -> b.info.difficulty.compareTo(a.info.difficulty)
        }
        is SortBy.Magic -> when (sortBy.direction) {
            SortDirection.Ascending -> a.info.magic.compareTo(b.info.magic)
            SortDirection.Descending -> b.info.magic.compareTo(a.info.magic)
        }
        is SortBy.Name -> when (sortBy.direction) {
            SortDirection.Ascending -> a.name.compareTo(b.name)
            SortDirection.Descending -> b.name.compareTo(a.name)
        }
    }
}

fun Champion.Companion.matcher(query: String): (Champion) -> Boolean = { champion ->
    query.split(" ").all { word ->
        champion.lore.contains(word, ignoreCase = true) ||
            champion.name.contains(word, ignoreCase = true) ||
            champion.tags.any { it.contains(word, ignoreCase = true) } ||
            champion.title.contains(word, ignoreCase = true)
    }
}

fun List<Version>.latest(): Version = max()
