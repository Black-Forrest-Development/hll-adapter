package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TemporaryBanAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TemporaryBanAction::class.java)
    }

    override fun getId() = "TemporaryBan"

    fun get(auth: Authentication): Set<String> {
        check(auth)
        TODO("Not yet implemented")
    }


    fun addByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }

    fun addBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }

    fun remove(auth: Authentication, player: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }


}
