package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerSwitchAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerSwitchAction::class.java)
    }

    override fun getId() = "PlayerSwitch"

    fun switchOnDeath(auth: Authentication, player: String): Any {
        check(auth)
        return client.sendCommand("switchteamondeath $player")
    }

    fun switchImmediately(auth: Authentication, player: String): Any {
        check(auth)
        return client.sendCommand("switchteamnow $player")
    }

}
