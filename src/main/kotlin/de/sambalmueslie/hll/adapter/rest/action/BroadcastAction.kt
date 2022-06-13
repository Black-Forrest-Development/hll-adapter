package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BroadcastAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BroadcastAction::class.java)
    }

    override fun getId() = "Broadcast"

    fun broadcast(auth: Authentication, message: String): Any {
        check(auth)
        return client.sendCommand("broadcast $message")
    }
}
