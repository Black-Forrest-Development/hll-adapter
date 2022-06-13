package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerAdminLogAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ServerAdminLogAction::class.java)
    }
	override fun getId() = "ServerAdminLog"

    fun get(auth: Authentication, minutes: Int, filter: String): List<String> {
        TODO("Not yet implemented")
    }

}
