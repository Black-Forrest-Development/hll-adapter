package de.sambalmueslie.hll.adapter.rest.api

data class PlayerInfo(
    val name: String,
    val steamID: String,
    val team: String,
    val role: String,
    val unit: String,
    val loadout: String,
    val kills: Int,
    val deaths: Int,
    val score: PlayerScore,
    val level: Int
)
