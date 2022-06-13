package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerPunishAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerPunishAction::class.java)
    }

    override fun getId() = "PlayerPunish"

    fun punish(auth: Authentication, player: String, reason: String): Any {
        check(auth)
        return client.sendCommand("punish $player $reason")
    }

}
