package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerSlotsAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerSlotsAction::class.java)
        const val ID = "ServerSlots"
    }

    override fun getId() = ID


    fun get(auth: Authentication, serverId: Long) = execute(auth, serverId, "get slots").let { Slots.parse(it) }

}
