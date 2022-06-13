package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerPlayerAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerPlayerAction::class.java)
    }
    override fun getId() = "ServerPlayer"

    fun get(auth: Authentication): Set<String> {
        check(auth)
//        return client.sendCommand("get players")
        TODO("Not yet implemented")
    }

}
