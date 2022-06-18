package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.builder.HllRconRequestBuilder
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageProfanityWordsAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageProfanityWordsAction::class.java)
        const val ID = "ManageProfanityWords"
    }

    override fun getId() = ID

    fun get(auth: Authentication) = getSet(auth, client, "get profanity")

    fun add(auth: Authentication, words: List<String>) = execute(auth, client) { HllRconRequestBuilder("banprofanity").add(words).build() }

    fun remove(auth: Authentication, word: String) = execute(auth, client, "unbanprofanity $word")

}
