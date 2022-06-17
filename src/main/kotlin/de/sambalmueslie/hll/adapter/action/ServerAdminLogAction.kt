package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerAdminLogAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ServerAdminLogAction::class.java)
        const val ID = "ServerAdminLog"
    }

    override fun getId() = ID


    fun get(auth: Authentication, minutes: Int, filter: String)= execute(auth, "showlog $minutes $filter") { client.getList(it) }

}
