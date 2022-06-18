package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TemporaryBanAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TemporaryBanAction::class.java)
        const val ID = "TemporaryBan"
    }

    override fun getId() = ID


    fun get(auth: Authentication) = getSet(auth, client, "get tempbans")


    fun addByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String) = execute(auth, client) {
        HllRconRequestBuilder("tempban").add(name).add(hours).escape(reason).add(admin).build()
    }

    fun addBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String) = execute(auth, client){
        HllRconRequestBuilder("tempban").add(steamId).add(hours).escape(reason).add(admin).build()
    }

    fun remove(auth: Authentication, player: String) = execute(auth, client, "pardontempban $player")

}
