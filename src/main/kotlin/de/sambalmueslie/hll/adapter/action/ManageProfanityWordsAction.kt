package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageProfanityWordsAction(clientService: RconClientService) : BaseAction(clientService,logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageProfanityWordsAction::class.java)
        const val ID = "ManageProfanityWords"
    }

    override fun getId() = ID

    fun get(auth: Authentication, serverId: Long) = getSet(auth, serverId, "get profanity")

    fun add(auth: Authentication, serverId: Long, words: List<String>) = execute(auth, serverId) { HllRconRequestBuilder("banprofanity").add(words).build() }

    fun remove(auth: Authentication, serverId: Long, word: String) = execute(auth, serverId, "unbanprofanity $word")

}
