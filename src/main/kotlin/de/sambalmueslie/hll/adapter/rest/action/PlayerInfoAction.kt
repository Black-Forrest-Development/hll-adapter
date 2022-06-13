package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerInfoAction(private val client: HllRconClient) : BaseAction() {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(PlayerInfoAction::class.java)
    }

    override fun getId() = "PlayerInfo"

    fun get(auth: Authentication, player: String): Any {
        check(auth)
        return client.sendCommand("playerinfo $player")
    }
}
