package fardavide.molecule.domain

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int
) : Comparable<Version> {

    val value: String
        get() = "$major.$minor.$patch"

    constructor(value: String) : this(
        value.substringAfter("lolpatch_").substringBefore('.').toInt(),
        value.substringAfter('.').substringBefore('.').toInt(),
        value.substringAfterLast('.', "0").toInt()
    )

    override fun compareTo(other: Version): Int {
        return when {
            major != other.major -> major.compareTo(other.major)
            minor != other.minor -> minor.compareTo(other.minor)
            else -> patch.compareTo(other.patch)
        }
    }

    companion object
}
