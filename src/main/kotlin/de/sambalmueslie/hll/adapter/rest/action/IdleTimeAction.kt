package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class IdleTimeAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(IdleTimeAction::class.java)
    }

    override fun getId() = "IdleTime"

    fun get(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get idletime").toIntOrNull() ?: -1
    }

    fun set(auth: Authentication, minutes: Int): Any {
        check(auth)
        return client.sendCommand("setkickidletime $minutes")
    }

}
