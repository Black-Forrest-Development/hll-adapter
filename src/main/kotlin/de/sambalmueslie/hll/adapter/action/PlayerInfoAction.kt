package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerInfoAction(private val client: HllRconClient) : BaseAction(logger) {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(PlayerInfoAction::class.java)
        const val ID = "PlayerInfo"
    }

    override fun getId() = ID


    fun get(auth: Authentication, player: String)= execute(auth,client, "playerinfo $player") 

}
