package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class ManageAdminAction(private val client: HllRconClient) : BaseAction(logger) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageAdminAction::class.java)
        const val ID = "ManageAdmin"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getSet(auth, client, "get adminids")

    fun add(auth: Authentication, steamId: String, group: String, comment: String) = execute(auth, client) {
        HllRconRequestBuilder("adminadd").add(steamId).add(group).escape(comment).build()
    }

    fun remove(auth: Authentication, steamId: String) = execute(auth, client, "admindel $steamId")

    fun getGroups(auth: Authentication) = getSet(auth, client, "get admingroups")


}
