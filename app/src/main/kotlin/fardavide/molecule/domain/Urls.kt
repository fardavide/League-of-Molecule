package fardavide.molecule.domain

object Urls {

    const val BASE = "https://ddragon.leagueoflegends.com"

    fun champions(version: Version) = "$BASE/cdn/${version.value}/data/en_US/champion.json"
    fun image(champion: Champion, version: Version) =
        "$BASE/cdn/${version.value}/img/champion/${champion.image.full}"
    fun versions() = "$BASE/api/versions.json"
}
