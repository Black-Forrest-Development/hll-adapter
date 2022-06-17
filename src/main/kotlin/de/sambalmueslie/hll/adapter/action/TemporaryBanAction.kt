package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TemporaryBanAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TemporaryBanAction::class.java)
        const val ID = "TemporaryBan"
    }

    override fun getId() = ID


    fun get(auth: Authentication)= execute(auth, "get tempbans") { client.getSet(it) }


    fun addByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String)= execute(auth, "tempban $name $hours \\\"$reason\\\" $admin") { client.sendCommand(it) }

    fun addBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String)= execute(auth, "tempban $steamId $hours \"$reason\" $admin") { client.sendCommand(it) }

    fun remove(auth: Authentication, player: String)= execute(auth, "pardontempban $player") { client.sendCommand(it) }

}
