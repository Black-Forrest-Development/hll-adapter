package de.sambalmueslie.hll.adapter.rest.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageProfanityWordsAction(private val client: HllRconClient) : BaseAction() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageProfanityWordsAction::class.java)
    }

    override fun getId() = "ManageProfanityWords"

    fun get(auth: Authentication): Set<String> {
        check(auth)
        return client.getSet("get profanity")
    }

    fun add(auth: Authentication, words: List<String>): Any {
        check(auth)
        return client.setList("banprofanity", words)
    }

    fun remove(auth: Authentication, word: String): Any {
        check(auth)
        return client.sendCommand("unbanprofanity $word")
    }

}
