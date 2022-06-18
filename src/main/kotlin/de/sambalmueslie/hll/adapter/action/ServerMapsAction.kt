package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerMapsAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerMapsAction::class.java)
        const val ID = "ServerMaps"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getSet(auth,client, "get mapsforrotation")

}
