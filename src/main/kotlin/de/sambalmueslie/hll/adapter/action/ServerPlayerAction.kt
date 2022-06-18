package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerPlayerAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerPlayerAction::class.java)
        const val ID = "ServerPlayer"
    }

    override fun getId() = ID

    fun get(auth: Authentication)= getSet(auth,client, "get players")

}
