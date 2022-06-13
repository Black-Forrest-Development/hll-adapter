package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HighPingAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(HighPingAction::class.java)
    }

    override fun getId() = "HighPing"


    fun get(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get highping").toIntOrNull() ?: -1
    }

    fun set(auth: Authentication, millis: Int): Any {
        check(auth)
        return client.sendCommand("sethighping $millis")
    }

}
