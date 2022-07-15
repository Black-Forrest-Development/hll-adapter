package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import de.sambalmueslie.hll.adapter.rest.api.Message
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerMessageAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerMessageAction::class.java)
        const val ID = "ServerMessage"
    }

    override fun getId() = ID

    fun set(auth: Authentication, serverId: Long, message: Message) = execute(auth, serverId) {
        HllRconRequestBuilder("say").escape(message.content).build()
    }

}
