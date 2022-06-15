package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PermanentBanAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PermanentBanAction::class.java)
    }

    override fun getId() = "PermanentBan"

    fun get(auth: Authentication): Set<String> {
        check(auth)
        return client.getSet("get permabans")
    }

    fun addByName(auth: Authentication, name: String, reason: String, admin: String): Any {
        check(auth)
        return client.sendCommand("permaban $name \"$reason\" $admin")
    }

    fun addBySteamId(auth: Authentication, steamId: String, reason: String, admin: String): Any {
        check(auth)
        return client.sendCommand("permaban $steamId \"$reason\" $admin")
    }

    fun remove(auth: Authentication, player: String): Any {
        check(auth)
        return client.sendCommand("pardonpermaban $player")
    }


}
