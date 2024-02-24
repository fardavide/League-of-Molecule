package fardavide.molecule.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Champion(
    val blurb: String,
    val id: String,
    val image: Image,
    val info: Info,
    val key: String,
    val name: String,
    @SerialName("partype") val parType: String,
    val stats: Stats,
    val tags: List<String>,
    val title: String,
    val version: String,
)

@Serializable
data class Image(
    val full: String,
    val group: String,
    @SerialName("h") val height: Int,
    val sprite: String,
    @SerialName("w") val width: Int,
    val x: Int,
    val y: Int,
)

@Serializable
data class Info(
    val attack: Int,
    val defense: Int,
    val difficulty: Int,
    val magic: Int,
)

@Serializable
data class Stats(
    val armor: Double,
    @SerialName("armorperlevel") val armorPerLevel: Double,
    @SerialName("attackdamage") val attackDamage: Double,
    @SerialName("attackdamageperlevel") val attackDamagePerLevel: Double,
    @SerialName("attackrange") val attackRange: Double,
    @SerialName("attackspeed") val attackSpeed: Double,
    @SerialName("attackspeedperlevel") val attackSpeedPerLevel: Double,
    val crit: Double,
    @SerialName("critperlevel") val critPerLevel: Double,
    val hp: Double,
    @SerialName("hpperlevel") val hpPerLevel: Double,
    @SerialName("hpregen") val hpRegen: Double,
    @SerialName("hpregenperlevel") val hpRegenPerLevel: Double,
    @SerialName("movespeed") val moveSpeed: Double,
    val mp: Double,
    @SerialName("mpperlevel") val mpPerLevel: Double,
    @SerialName("mpregen") val mpRegen: Double,
    @SerialName("mpregenperlevel") val mpRegenPerLevel: Double,
    @SerialName("spellblock") val spellBlock: Double,
    @SerialName("spellblockperlevel") val spellBlockPerLevel: Double,
)
