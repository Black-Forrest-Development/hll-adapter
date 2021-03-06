package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerPlayerAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerPlayerAction::class.java)
        const val ID = "ServerPlayer"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get players")

}
