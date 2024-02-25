package fardavide.molecule.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Champion(
    val id: String,
    val image: Image,
    val info: Info,
    val key: String,
    @SerialName("blurb") val lore: String,
    val name: String,
    @SerialName("partype") val parType: String,
    val stats: Stats,
    val tags: List<String>,
    val title: String,
    val version: String
) {

    companion object
}

@Serializable
data class Image(
    val full: String,
    val group: String,
    @SerialName("h") val height: Int,
    val sprite: String,
    @SerialName("w") val width: Int,
    val x: Int,
    val y: Int
)

@Serializable
data class Info(
    val attack: Int,
    val defense: Int,
    val difficulty: Int,
    val magic: Int
)

@Serializable
data class Stats(
    @SerialName("armor") val armor: Double,
    @SerialName("armorperlevel") val armorPerLevel: Double,

    @SerialName("attackdamage") val attackDamage: Double,
    @SerialName("attackdamageperlevel") val attackDamagePerLevel: Double,

    @SerialName("attackrange") val attackRange: Double,

    @SerialName("attackspeed") val attackSpeed: Double,
    @SerialName("attackspeedperlevel") val attackSpeedPerLevel: Double,

    @SerialName("crit") val crit: Double,
    @SerialName("critperlevel") val critPerLevel: Double,

    @SerialName("hp") val hp: Double,
    @SerialName("hpperlevel") val hpPerLevel: Double,
    @SerialName("hpregen") val hpRegen: Double,
    @SerialName("hpregenperlevel") val hpRegenPerLevel: Double,

    @SerialName("spellblock") val magicDefence: Double,
    @SerialName("spellblockperlevel") val magicDefencePerLevel: Double,

    @SerialName("mp") val mana: Double,
    @SerialName("mpperlevel") val manaPerLevel: Double,
    @SerialName("mpregen") val manaRegen: Double,
    @SerialName("mpregenperlevel") val manaRegenPerLevel: Double,

    @SerialName("movespeed") val moveSpeed: Double
)
