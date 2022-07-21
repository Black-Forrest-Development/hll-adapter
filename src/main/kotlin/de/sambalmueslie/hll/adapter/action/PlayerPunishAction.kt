package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerPunishAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerPunishAction::class.java)
        const val ID = "PlayerPunish"
    }

    override fun getId() = ID

    fun punish(auth: Authentication, serverId: Long, player: String, reason: String) = execute(auth, serverId) {
        HllRconRequestBuilder("punish").escape(player).escape(reason).build()
    }

}
