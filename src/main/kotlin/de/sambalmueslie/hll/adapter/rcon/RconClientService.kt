package de.sambalmueslie.hll.adapter.rcon


import de.sambalmueslie.hll.adapter.server.ServerConfigService
import de.sambalmueslie.hll.adapter.server.api.ServerConfig
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class RconClientService(private val serverConfigService: ServerConfigService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconClientService::class.java)
    }

    private val clientCache: MutableMap<Long, RconClient> = mutableMapOf()


    fun getClient(auth: Authentication, serverId: Long): RconClient? {
        val data = serverConfigService.get(auth, serverId) ?: return null
        return clientCache[data.id] ?: create(data)
    }

    private fun create(data: ServerConfig): RconClient {
        val client = RconClient(RconClientConfig(data.host, data.port, data.password))
        clientCache[data.id] = client
        return client
    }


}
