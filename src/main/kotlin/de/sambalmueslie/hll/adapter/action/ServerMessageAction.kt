package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.ExecuteActionResponse
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

    fun set(auth: Authentication, serverId: Long, message: Message): ExecuteActionResponse {
        val request = HllRconRequestBuilder("say").escape(message.content).build()
        return try {
            val result = execute(auth, serverId, request)
            ExecuteActionResponse(ID, message.content, result == "SUCCESS")
        } catch (e: Exception) {
            ExecuteActionResponse(ID, message.content, false)
        }
    }

}
