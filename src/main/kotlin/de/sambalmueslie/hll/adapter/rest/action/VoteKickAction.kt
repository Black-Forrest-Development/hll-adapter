package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VoteKickAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(VoteKickAction::class.java)
    }

    override fun getId() = "VoteKick"
    fun isEnabled(auth: Authentication): Boolean {
        check(auth)
        return client.sendCommand("get votekickenabled") == "on"
    }

    fun setEnabled(auth: Authentication, enabled: Boolean): Any {
        check(auth)
        return client.sendCommand("setvotekickenabled ${if (enabled) "on" else "off"}")
    }

    fun getThreshold(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get votekickthreshold").toIntOrNull() ?: -1
    }

    fun setThreshold(auth: Authentication, threshold: Int): Any {
        check(auth)
        return client.sendCommand("setvotekickthreshold $threshold")
    }

    fun resetThreshold(auth: Authentication): Any {
        check(auth)
        return client.sendCommand("resetvotekickthreshold")
    }

}
