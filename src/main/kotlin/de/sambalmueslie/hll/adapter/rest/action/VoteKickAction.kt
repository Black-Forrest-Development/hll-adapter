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
        return client.getBoolean("get votekickenabled")
    }

    fun setEnabled(auth: Authentication, enabled: Boolean): Any {
        check(auth)
        return client.setBoolean("setvotekickenabled", enabled)
    }

    fun getThreshold(auth: Authentication): Int {
        check(auth)
        return client.getInt("get votekickthreshold")
    }

    fun setThreshold(auth: Authentication, threshold: Int): Any {
        check(auth)
        return client.setInt("setvotekickthreshold", threshold)
    }

    fun resetThreshold(auth: Authentication): Any {
        check(auth)
        return client.sendCommand("resetvotekickthreshold")
    }

}
