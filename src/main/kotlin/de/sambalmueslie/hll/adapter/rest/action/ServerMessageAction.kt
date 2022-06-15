package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rest.api.Message
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerMessageAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerMessageAction::class.java)
    }

    override fun getId() = "ServerMessage"

    fun set(auth: Authentication, message: Message): Any {
        check(auth)
        return client.sendCommand("say \"${message.content}\"")
    }

}
