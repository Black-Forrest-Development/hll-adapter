package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerKickAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerKickAction::class.java)
    }

    override fun getId() = "PlayerKick"

    fun kick(auth: Authentication, player: String, reason: String): Any {
        check(auth)
        return client.sendCommand("kick \"$player\" \"$reason\"")
    }

}
