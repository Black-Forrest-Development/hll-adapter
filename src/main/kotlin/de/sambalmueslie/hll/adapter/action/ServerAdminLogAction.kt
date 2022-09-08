package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconRequest
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerAdminLogAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ServerAdminLogAction::class.java)
        const val ID = "ServerAdminLog"
    }

    override fun getId() = ID


    fun get(auth: Authentication, serverId: Long, minutes: Int, filter: String) = execute(auth, serverId, HllRconRequest("showlog $minutes $filter")) {
        logger.info("Got ${it.content.length}")
        it.content.split("\n")
    }

}
