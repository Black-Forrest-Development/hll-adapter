package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AutoBalanceAction(private val client: HllRconClient) : de.sambalmueslie.hll.adapter.action.BaseAction(logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(de.sambalmueslie.hll.adapter.action.AutoBalanceAction::class.java)
        const val ID = "AutoBalance"
    }

    override fun getId() = ID
    fun getThreshold(auth: Authentication) = execute(auth, "get autobalancethreshold") { client.getInt(it) }
    fun setThreshold(auth: Authentication, threshold: Int) = execute(auth, "setautobalancethreshold $threshold") { client.sendCommand(it) }
    fun isEnabled(auth: Authentication) = execute(auth, "get autobalanceenabled") { client.getBoolean(it) }
    fun setEnabled(auth: Authentication, enabled: Boolean) = execute(auth, "setautobalanceenabled") { client.setBoolean(it, enabled) }

}
