package de.sambalmueslie.hll.adapter.rcon


import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class RconClientService {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconClientService::class.java)
    }

    fun getClient(serverId: Long): RconClient? {
        TODO("not implemented yet")
    }


}
