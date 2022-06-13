package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MaxQueuedPlayersAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MaxQueuedPlayersAction::class.java)
    }

    override fun getId() = "MaxQueuedPlayers"

    fun set(auth: Authentication, max: Int): Any {
        check(auth)
        return client.sendCommand("setmaxqueuedplayers $max")
    }

    fun get(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get maxqueuedplayers").toIntOrNull() ?: -1
    }

}
