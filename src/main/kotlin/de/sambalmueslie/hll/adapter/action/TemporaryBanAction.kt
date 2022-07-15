package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TemporaryBanAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TemporaryBanAction::class.java)
        const val ID = "TemporaryBan"
    }

    override fun getId() = ID


    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get tempbans")


    fun addByName(auth: Authentication, serverId: Long, name: String, hours: Int, reason: String, admin: String) = execute(auth, serverId) {
        HllRconRequestBuilder("tempban").add(name).add(hours).escape(reason).add(admin).build()
    }

    fun addBySteamId(auth: Authentication, serverId: Long, steamId: String, hours: Int, reason: String, admin: String) = execute(auth, serverId){
        HllRconRequestBuilder("tempban").add(steamId).add(hours).escape(reason).add(admin).build()
    }

    fun remove(auth: Authentication, serverId: Long, player: String) = execute(auth, serverId, "pardontempban $player")

}
