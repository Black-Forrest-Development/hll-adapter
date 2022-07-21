package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageVipAction(clientService: RconClientService) : BaseAction(clientService, logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageVipAction::class.java)
        const val ID = "ManageVip"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get vipids")

    fun add(auth: Authentication, serverId: Long, steamId: String, description: String) = execute(auth, serverId) {
        HllRconRequestBuilder("vipadd").add(steamId).escape(description).build()
    }

    fun remove(auth: Authentication, serverId: Long, steamId: String) = execute(auth, serverId, "vipdel $steamId")

    fun getSlots(auth: Authentication, serverId: Long) = getInt(auth, serverId, "get numvipslots")

    fun setSlots(auth: Authentication, serverId: Long, max: Int) = execute(auth, serverId, "setnumvipslots $max")
}
