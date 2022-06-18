package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HighPingAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(HighPingAction::class.java)
        const val ID = "HighPing"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getInt(auth, client, "get highping")

    fun set(auth: Authentication, millis: Int) = setValue(auth, client, "sethighping $millis")

}
