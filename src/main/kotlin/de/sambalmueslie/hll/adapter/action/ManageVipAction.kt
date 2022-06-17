package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageVipAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageVipAction::class.java)
        const val ID = "ManageVip"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = execute(auth, "get vipids") { client.getSet(it) }

    fun add(auth: Authentication, steamId: String, description: String) = execute(auth, "vipadd $steamId \"$description\"") { client.sendCommand(it) }

    fun remove(auth: Authentication, steamId: String) = execute(auth, "vipdel $steamId") { client.sendCommand(it) }

    fun getSlots(auth: Authentication) = execute(auth, "get numvipslots") { client.getInt(it) }

    fun setSlots(auth: Authentication, max: Int) = execute(auth, "setnumvipslots $max") { client.sendCommand(it) }
}
