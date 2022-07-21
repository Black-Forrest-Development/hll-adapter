package de.sambalmueslie.hll.adapter.server.api

data class ServerConfig(
    val id: Long,
    val name: String,
    val host: String,
    val port: Int,
    val password: String
)
