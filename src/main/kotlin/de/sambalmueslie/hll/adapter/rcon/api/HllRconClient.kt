package de.sambalmueslie.hll.adapter.rcon.api

interface HllRconClient {
    fun connect()
    fun sendCommand(command: String): String
    fun disconnect()
}
