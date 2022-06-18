package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import de.sambalmueslie.hll.adapter.rest.api.Message
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BroadcastAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BroadcastAction::class.java)
        const val ID = "Broadcast"
    }

    override fun getId() = ID

    fun broadcast(auth: Authentication, message: Message) = execute(auth, client) { HllRconRequestBuilder("broadcast").escape(message.content).build() }

}
