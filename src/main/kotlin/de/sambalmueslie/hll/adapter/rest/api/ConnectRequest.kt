package de.sambalmueslie.hll.adapter.rest.api

data class ConnectRequest(
    val host: String,
    val port: Int,
    val password: String
)
