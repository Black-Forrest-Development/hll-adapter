package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerNameAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerNameAction::class.java)
    }

    override fun getId() =  "ServerName"

    fun get(auth: Authentication): String {
        check(auth)
        return client.sendCommand("get name")
    }

}
