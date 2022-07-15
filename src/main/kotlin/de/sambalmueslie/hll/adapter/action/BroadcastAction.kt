package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import de.sambalmueslie.hll.adapter.rest.api.Message
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BroadcastAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BroadcastAction::class.java)
        const val ID = "Broadcast"
    }

    override fun getId() = ID

    fun broadcast(auth: Authentication, serverId: Long, message: Message) = execute(auth, serverId) { HllRconRequestBuilder("broadcast").escape(message.content).build() }

}
