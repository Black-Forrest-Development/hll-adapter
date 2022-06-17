package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class ManageAdminAction(private val client: HllRconClient) : BaseAction(logger) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageAdminAction::class.java)
        const val ID = "ManageAdmin"
    }

    override fun getId() = ID

    fun get(auth: Authentication): Set<String> = execute(auth, "get adminids") { client.getSet(it) }

    fun add(auth: Authentication, steamId: String, group: String, comment: String) = execute(auth, "adminadd $steamId $group \"$comment\"") { client.sendCommand(it) }

    fun remove(auth: Authentication, steamId: String) = execute(auth, "admindel $steamId") { client.sendCommand(it) }

    fun getGroups(auth: Authentication) = execute(auth, "get admingroups") { client.getSet(it) }


}
