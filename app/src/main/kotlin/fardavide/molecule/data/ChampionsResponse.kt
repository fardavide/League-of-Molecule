package fardavide.molecule.data

import fardavide.molecule.domain.Champion
import kotlinx.serialization.Serializable

@Serializable
data class ChampionsResponse(
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, Champion>
)
