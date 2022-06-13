package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerMapsAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerMapsAction::class.java)
    }

    override fun getId() = "ServerMaps"

    fun get(auth: Authentication): Set<String> {
        check(auth)
        TODO("Not yet implemented")
    }

}
