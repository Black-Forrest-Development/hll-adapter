package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ManageProfanityWordsAction(private val client: HllRconClient) : BaseAction(logger) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ManageProfanityWordsAction::class.java)
        const val ID = "ManageProfanityWords"
    }

    override fun getId() = ID

    fun get(auth: Authentication)= execute(auth, "get profanity") { client.getSet(it) }

    fun add(auth: Authentication, words: List<String>)= execute(auth, "banprofanity") { client.setList(it,words) }

    fun remove(auth: Authentication, word: String)= execute(auth, "unbanprofanity $word") { client.sendCommand(it) }

}
