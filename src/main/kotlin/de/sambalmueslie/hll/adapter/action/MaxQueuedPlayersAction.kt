package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MaxQueuedPlayersAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MaxQueuedPlayersAction::class.java)
        const val ID = "MaxQueuedPlayers"
    }

    override fun getId() = ID

    fun set(auth: Authentication, serverId: Long, max: Int)= execute(auth, serverId,"setmaxqueuedplayers $max")

    fun get(auth: Authentication, serverId: Long)= getInt(auth, serverId,"get maxqueuedplayers")

}
