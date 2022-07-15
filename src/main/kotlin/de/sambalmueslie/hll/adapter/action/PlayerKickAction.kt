package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerKickAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerKickAction::class.java)
        const val ID = "PlayerKick"
    }

    override fun getId() = ID

    fun kick(auth: Authentication, serverId: Long, player: String, reason: String) = execute(auth, serverId) {
        HllRconRequestBuilder("kick").escape(player).escape(reason).build()
    }

}
