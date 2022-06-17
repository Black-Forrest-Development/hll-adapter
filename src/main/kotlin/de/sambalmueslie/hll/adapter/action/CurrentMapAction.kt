package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CurrentMapAction(private val client: HllRconClient) : BaseAction(logger) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CurrentMapAction::class.java)
        const val ID = "CurrentMap"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = execute(auth, "get map") { client.sendCommand(it) }

    fun set(auth: Authentication, name: String) = execute(auth, "map $name") { client.sendCommand(it) }

}
