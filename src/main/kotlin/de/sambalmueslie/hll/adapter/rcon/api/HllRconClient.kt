package de.sambalmueslie.hll.adapter.rcon.api

import de.sambalmueslie.hll.adapter.rcon.RconClientConfig

interface HllRconClient {
    fun connect(config: RconClientConfig)

    @Deprecated("use send with request and response")
    fun sendCommand(command: String): String
    fun disconnect()

    fun send(request: HllRconRequest): HllRconResponse

}
