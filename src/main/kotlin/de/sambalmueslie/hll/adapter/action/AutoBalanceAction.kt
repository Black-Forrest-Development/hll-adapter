package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AutoBalanceAction(private val client: HllRconClient) : de.sambalmueslie.hll.adapter.action.BaseAction(logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(de.sambalmueslie.hll.adapter.action.AutoBalanceAction::class.java)
        const val ID = "AutoBalance"
    }

    override fun getId() = ID
    fun getThreshold(auth: Authentication) = getInt(auth, client, "get autobalancethreshold")
    fun setThreshold(auth: Authentication, threshold: Int) = setValue(auth, client) { HllRconRequestBuilder("setautobalancethreshold").add(threshold).build() }
    fun isEnabled(auth: Authentication) = getBoolean(auth, client, "get autobalanceenabled")
    fun setEnabled(auth: Authentication, enabled: Boolean) = setValue(auth, client) { HllRconRequestBuilder("setautobalanceenabled").add(enabled).build() }


}
