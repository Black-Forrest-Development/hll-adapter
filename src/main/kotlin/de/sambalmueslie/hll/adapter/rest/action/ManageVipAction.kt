package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageVipAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageVipAction::class.java)
    }

    override fun getId() = "ManageVip"

    fun get(auth: Authentication): Set<String> {
        check(auth)
        TODO("Not yet implemented")
    }

    fun add(auth: Authentication, steamId: String, description: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }

    fun remove(auth: Authentication, steamId: String): Any {
        check(auth)
        TODO("Not yet implemented")
    }

    fun getSlots(auth: Authentication): Int {
        check(auth)
        return client.sendCommand("get numvipslots").toIntOrNull() ?: -1
    }

    fun setSlots(auth: Authentication, max: Int): Any {
        check(auth)
        return client.sendCommand("setnumvipslots $max")
    }
}
