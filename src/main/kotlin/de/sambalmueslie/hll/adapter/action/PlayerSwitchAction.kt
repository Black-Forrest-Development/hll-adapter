package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerSwitchAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerSwitchAction::class.java)
        const val ID = "PlayerSwitch"
    }

    override fun getId() = ID

    fun switchOnDeath(auth: Authentication, player: String)= execute(auth, client,"switchteamondeath $player")

    fun switchImmediately(auth: Authentication, player: String)= execute(auth,client, "switchteamnow $player")

}
