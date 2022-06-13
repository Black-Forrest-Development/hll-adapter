package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerSlotsAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerSlotsAction::class.java)
    }

    override fun getId() = "ServerSlots"

    fun get(auth: Authentication): Slots {
        check(auth)
        return Slots.parse(client.sendCommand("get slots"))
    }


}
