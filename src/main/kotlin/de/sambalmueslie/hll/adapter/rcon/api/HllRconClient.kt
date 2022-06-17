package de.sambalmueslie.hll.adapter.rcon.api

import de.sambalmueslie.hll.adapter.rcon.RconClientConfig

interface HllRconClient {
    fun connect(config: RconClientConfig)
    fun sendCommand(command: String): String
    fun disconnect()
    fun getSet(command: String): Set<String>
    fun getInt(command: String): Int

    @Deprecated("create an builder")
    fun setInt(command: String, value: Int): String
    fun getBoolean(command: String): Boolean

    @Deprecated("create an builder")
    fun setBoolean(command: String, value: Boolean): String

    @Deprecated("create an builder")
    fun setList(command: String, words: List<String>): String
    fun getList(command: String): List<String>
}
