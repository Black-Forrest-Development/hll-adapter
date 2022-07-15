package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VoteKickAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(VoteKickAction::class.java)
        const val ID = "VoteKick"
    }

    override fun getId() = ID

    fun isEnabled(auth: Authentication, serverId: Long) = getBoolean(auth, serverId, "get votekickenabled")

    fun setEnabled(auth: Authentication, serverId: Long, enabled: Boolean) = execute(auth, serverId) {
        HllRconRequestBuilder("setvotekickenabled").add(enabled).build()
    }

    fun getThreshold(auth: Authentication, serverId: Long) = getInt(auth, serverId, "get votekickthreshold")

    fun setThreshold(auth: Authentication, serverId: Long, threshold: Int) = execute(auth, serverId) {
        HllRconRequestBuilder("setvotekickthreshold").add(threshold).build()
    }

    fun resetThreshold(auth: Authentication, serverId: Long) = execute(auth, serverId, "resetvotekickthreshold")


}
