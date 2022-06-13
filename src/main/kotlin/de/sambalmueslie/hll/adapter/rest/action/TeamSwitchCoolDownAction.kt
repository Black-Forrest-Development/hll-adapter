package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TeamSwitchCoolDownAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TeamSwitchCoolDownAction::class.java)
    }

    override fun getId() = "TeamSwitchCoolDown"

    fun get(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get teamswitchcooldown").toIntOrNull() ?: -1
    }

    fun set(auth: Authentication, coolDown: Int): Any {
        check(auth)
        return client.sendCommand("setteamswitchcooldown $coolDown")
    }

}
