package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PermanentBanAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PermanentBanAction::class.java)
        const val ID = "PermanentBan"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get permabans")

    fun addByName(auth: Authentication, serverId: Long, name: String, reason: String, admin: String) = execute(auth, serverId) {
        HllRconRequestBuilder("permaban").add(name).escape(reason).add(admin).build()
    }

    fun addBySteamId(auth: Authentication, serverId: Long, steamId: String, reason: String, admin: String) = execute(auth, serverId) {
        HllRconRequestBuilder("permaban").add(steamId).escape(reason).add(admin).build()
    }

    fun remove(auth: Authentication, serverId: Long, player: String) = execute(auth, serverId, "pardonpermaban $player")

}
