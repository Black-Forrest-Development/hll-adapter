package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MapRotationAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MapRotationAction::class.java)
        const val ID = "MapRotation"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getSet(auth, client, "rotlist")

    fun add(auth: Authentication, name: String) = execute(auth, client, "rotadd $name")

    fun remove(auth: Authentication, name: String) = execute(auth, client, "rotdel $name")

}
