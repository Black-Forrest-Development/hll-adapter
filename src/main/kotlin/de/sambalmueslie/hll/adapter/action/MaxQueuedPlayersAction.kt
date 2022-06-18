package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MaxQueuedPlayersAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MaxQueuedPlayersAction::class.java)
        const val ID = "MaxQueuedPlayers"
    }

    override fun getId() = ID

    fun set(auth: Authentication, max: Int)= execute(auth, client,"setmaxqueuedplayers $max")

    fun get(auth: Authentication)= getInt(auth, client,"get maxqueuedplayers")

}
