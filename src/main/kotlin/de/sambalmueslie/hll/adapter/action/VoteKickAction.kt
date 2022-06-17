package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VoteKickAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(VoteKickAction::class.java)
        const val ID = "VoteKick"
    }

    override fun getId() = ID

    fun isEnabled(auth: Authentication)= execute(auth, "get votekickenabled") { client.getBoolean(it) }

    fun setEnabled(auth: Authentication, enabled: Boolean)= execute(auth, "setvotekickenabled") { client.setBoolean(it,enabled) }

    fun getThreshold(auth: Authentication)= execute(auth, "get votekickthreshold") { client.getInt(it) }

    fun setThreshold(auth: Authentication, threshold: Int)= execute(auth, "setvotekickthreshold") { client.setInt(it,threshold) }

    fun resetThreshold(auth: Authentication)= execute(auth, "resetvotekickthreshold") { client.sendCommand(it) }


}
