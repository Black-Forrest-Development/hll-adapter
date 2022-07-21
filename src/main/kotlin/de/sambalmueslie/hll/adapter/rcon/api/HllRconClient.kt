package de.sambalmueslie.hll.adapter.rcon.api

interface HllRconClient {

    fun send(request: HllRconRequest): HllRconResponse

    fun disconnect()
}
