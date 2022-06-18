package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerSlotsAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerSlotsAction::class.java)
        const val ID = "ServerSlots"
    }

    override fun getId() = ID


    fun get(auth: Authentication) = execute(auth, client, "get slots").let { Slots.parse(it) }

}
