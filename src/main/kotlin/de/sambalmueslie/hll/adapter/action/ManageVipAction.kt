package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageVipAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageVipAction::class.java)
        const val ID = "ManageVip"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getSet(auth, client, "get vipids")

    fun add(auth: Authentication, steamId: String, description: String) = execute(auth, client) {
        HllRconRequestBuilder("vipadd").add(steamId).escape(description).build()
    }

    fun remove(auth: Authentication, steamId: String) = execute(auth, client, "vipdel $steamId")

    fun getSlots(auth: Authentication) = getInt(auth, client, "get numvipslots")

    fun setSlots(auth: Authentication, max: Int) = execute(auth, client, "setnumvipslots $max")
}
