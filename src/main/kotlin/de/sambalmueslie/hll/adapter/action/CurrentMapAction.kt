package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CurrentMapAction(clientService: RconClientService) : BaseAction(clientService,logger) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CurrentMapAction::class.java)
        const val ID = "CurrentMap"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getString(auth, serverId, "get map")

    fun set(auth: Authentication, serverId: Long, name: String) = setValue(auth, serverId, "map $name")

}
