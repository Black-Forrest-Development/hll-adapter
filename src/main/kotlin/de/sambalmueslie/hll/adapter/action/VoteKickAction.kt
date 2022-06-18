package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VoteKickAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(VoteKickAction::class.java)
        const val ID = "VoteKick"
    }

    override fun getId() = ID

    fun isEnabled(auth: Authentication) = getBoolean(auth, client, "get votekickenabled")

    fun setEnabled(auth: Authentication, enabled: Boolean) = execute(auth, client) {
        HllRconRequestBuilder("setvotekickenabled").add(enabled).build()
    }

    fun getThreshold(auth: Authentication) = getInt(auth, client, "get votekickthreshold")

    fun setThreshold(auth: Authentication, threshold: Int) = execute(auth, client) {
        HllRconRequestBuilder("setvotekickthreshold").add(threshold).build()
    }

    fun resetThreshold(auth: Authentication) = execute(auth, client, "resetvotekickthreshold")


}
