package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class IdleTimeAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(IdleTimeAction::class.java)
        const val ID = "IdleTime"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getInt(auth, client, "get idletime")

    fun set(auth: Authentication, minutes: Int) = setValue(auth, client, "setkickidletime $minutes")

}