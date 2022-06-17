package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PermanentBanAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PermanentBanAction::class.java)
        const val ID = "PermanentBan"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = execute(auth, "get permabans") { client.getSet(it) }

    fun addByName(auth: Authentication, name: String, reason: String, admin: String) = execute(auth, "permaban $name \"$reason\" $admin") { client.sendCommand(it) }

    fun addBySteamId(auth: Authentication, steamId: String, reason: String, admin: String) = execute(auth, "permaban $steamId \"$reason\" $admin") { client.sendCommand(it) }

    fun remove(auth: Authentication, player: String) = execute(auth, "pardonpermaban $player") { client.sendCommand(it) }

}
