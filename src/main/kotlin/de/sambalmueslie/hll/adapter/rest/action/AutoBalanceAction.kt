package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AutoBalanceAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(AutoBalanceAction::class.java)
    }

    override fun getId() = "AutoBalance"

    fun getThreshold(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get autobalancethreshold").toIntOrNull() ?: -1
    }

    fun setThreshold(auth: Authentication, threshold: Int): Any {
        check(auth)
        return client.sendCommand("setautobalancethreshold $threshold")
    }

    fun isEnabled(auth: Authentication): Boolean {
        check(auth)
        return client.sendCommand("get autobalanceenabled") == "on"
    }

    fun setEnabled(auth: Authentication, enabled: Boolean): Any {
        check(auth)
        return client.sendCommand("setautobalanceenabled ${if (enabled) "on" else "off"}")
    }

}
