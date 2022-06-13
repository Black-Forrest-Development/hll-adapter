package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageAdminAction(private val client: HllRconClient) : BaseAction() {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageAdminAction::class.java)
    }

    override fun getId() = "ManageAdmin"

    fun get(auth: Authentication): Set<String> {
        check(auth)
        TODO("Not yet implemented")
    }

    fun add(auth: Authentication, steamId: String, group: String, comment: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }

    fun remove(auth: Authentication, steamId: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }

    fun getGroups(auth: Authentication): Set<String> {
        check(auth)
        TODO("Not yet implemented")
    }
}
