package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class IdleTimeAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(IdleTimeAction::class.java)
        const val ID = "IdleTime"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getInt(auth, serverId, "get idletime")

    fun set(auth: Authentication, serverId: Long, minutes: Int) = setValue(auth, serverId, "setkickidletime $minutes")

}
