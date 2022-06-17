package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerPunishAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerPunishAction::class.java)
        const val ID = "PlayerPunish"
    }

    override fun getId() = ID

    fun punish(auth: Authentication, player: String, reason: String)= execute(auth, "punish \"$player\" \"$reason\"") { client.sendCommand(it) }

}
