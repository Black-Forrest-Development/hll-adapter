package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rest.parser.PlayerInfoParser
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerInfoAction(clientService: RconClientService) : BaseAction(clientService,logger) {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(PlayerInfoAction::class.java)
        const val ID = "PlayerInfo"
    }

    override fun getId() = ID

    private val parser = PlayerInfoParser()
    fun get(auth: Authentication, serverId: Long, player: String) = execute(auth, serverId, "playerinfo $player").let { parser.parsePlayerInfo(it) }


}

