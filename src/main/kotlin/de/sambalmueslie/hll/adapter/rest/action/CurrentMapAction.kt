package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CurrentMapAction(private val client: HllRconClient) : BaseAction() {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CurrentMapAction::class.java)
    }

    override fun getId() = "CurrentMap"

    fun get(auth: Authentication): String {
        check(auth)
        return client.sendCommand("get map")
    }

    fun set(auth: Authentication, name: String): Any {
        check(auth)
        return client.sendCommand("map $name")
    }
}
