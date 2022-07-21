package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MapRotationAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MapRotationAction::class.java)
        const val ID = "MapRotation"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "rotlist")

    fun add(auth: Authentication, serverId: Long, name: String) = execute(auth, serverId, "rotadd $name")

    fun remove(auth: Authentication, serverId: Long, name: String) = execute(auth, serverId, "rotdel $name")

}
