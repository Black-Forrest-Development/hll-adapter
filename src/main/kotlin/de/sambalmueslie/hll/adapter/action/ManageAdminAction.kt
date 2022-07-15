package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class ManageAdminAction(clientService: RconClientService) : BaseAction(clientService,logger) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageAdminAction::class.java)
        const val ID = "ManageAdmin"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get adminids")

    fun add(auth: Authentication, serverId: Long, steamId: String, group: String, comment: String) = execute(auth, serverId) {
        HllRconRequestBuilder("adminadd").add(steamId).add(group).escape(comment).build()
    }

    fun remove(auth: Authentication, serverId: Long, steamId: String) = execute(auth, serverId, "admindel $steamId")

    fun getGroups(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get admingroups")


}
