package de.sambalmueslie.hll.adapter.server.api

data class ServerConfigChangeRequest(
    val name: String,
    val host: String,
    val port: Int,
    val password: String
)
