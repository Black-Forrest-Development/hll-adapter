package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HighPingAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(HighPingAction::class.java)
        const val ID = "HighPing"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getInt(auth, serverId, "get highping")

    fun set(auth: Authentication, serverId: Long, millis: Int) = setValue(auth, serverId, "sethighping $millis")

}
