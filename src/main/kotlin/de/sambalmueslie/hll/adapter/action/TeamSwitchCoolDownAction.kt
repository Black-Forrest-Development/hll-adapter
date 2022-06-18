package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TeamSwitchCoolDownAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TeamSwitchCoolDownAction::class.java)
        const val ID = "TeamSwitchCoolDown"
    }

    override fun getId() = ID


    fun get(auth: Authentication) = getInt(auth, client, "get teamswitchcooldown")

    fun set(auth: Authentication, coolDown: Int) = execute(auth, client, "setteamswitchcooldown $coolDown")
}
