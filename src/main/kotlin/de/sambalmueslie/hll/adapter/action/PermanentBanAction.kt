package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PermanentBanAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PermanentBanAction::class.java)
        const val ID = "PermanentBan"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getSet(auth, client, "get permabans")

    fun addByName(auth: Authentication, name: String, reason: String, admin: String) = execute(auth, client) {
        HllRconRequestBuilder("permaban").add(name).escape(reason).add(admin).build()
    }

    fun addBySteamId(auth: Authentication, steamId: String, reason: String, admin: String) = execute(auth, client) {
        HllRconRequestBuilder("permaban").add(steamId).escape(reason).add(admin).build()
    }

    fun remove(auth: Authentication, player: String) = execute(auth, client, "pardonpermaban $player")

}
