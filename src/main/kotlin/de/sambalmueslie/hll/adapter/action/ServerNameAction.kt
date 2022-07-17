package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.GetActionResponse
import de.sambalmueslie.hll.adapter.rcon.RconClientService
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerNameAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerNameAction::class.java)
        const val ID = "ServerName"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = GetActionResponse(execute(auth, serverId, "get name"))
}
