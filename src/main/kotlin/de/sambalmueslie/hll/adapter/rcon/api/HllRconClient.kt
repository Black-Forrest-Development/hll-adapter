package de.sambalmueslie.hll.adapter.rcon.api

interface HllRconClient {
    fun connect()
    fun sendCommand(command: String): String
    fun disconnect()
    fun getSet(command: String): Set<String>
    fun getInt(command: String): Int
    fun setInt(command: String, value: Int): String
    fun getBoolean(command: String): Boolean
    fun setBoolean(command: String, value: Boolean): String
    fun setList(command: String, words: List<String>): String
    fun getList(command: String): List<String>
}
