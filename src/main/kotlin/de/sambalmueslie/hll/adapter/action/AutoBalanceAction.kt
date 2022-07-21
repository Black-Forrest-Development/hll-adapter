package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AutoBalanceAction(clientService: RconClientService) : de.sambalmueslie.hll.adapter.action.BaseAction(clientService, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(de.sambalmueslie.hll.adapter.action.AutoBalanceAction::class.java)
        const val ID = "AutoBalance"
    }

    override fun getId() = ID
    fun getThreshold(auth: Authentication, serverId: Long) = getInt(auth, serverId, "get autobalancethreshold")
    fun setThreshold(auth: Authentication, serverId: Long, threshold: Int) = setValue(auth, serverId) { HllRconRequestBuilder("setautobalancethreshold").add(threshold).build() }
    fun isEnabled(auth: Authentication, serverId: Long) = getBoolean(auth, serverId, "get autobalanceenabled")
    fun setEnabled(auth: Authentication, serverId: Long, enabled: Boolean) = setValue(auth, serverId) { HllRconRequestBuilder("setautobalanceenabled").add(enabled).build() }


}
