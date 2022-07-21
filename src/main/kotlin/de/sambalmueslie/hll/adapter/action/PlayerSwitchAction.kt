package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerSwitchAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerSwitchAction::class.java)
        const val ID = "PlayerSwitch"
    }

    override fun getId() = ID

    fun switchOnDeath(auth: Authentication, serverId: Long, player: String) = execute(auth, serverId, "switchteamondeath $player")

    fun switchImmediately(auth: Authentication, serverId: Long, player: String) = execute(auth, serverId, "switchteamnow $player")

}
