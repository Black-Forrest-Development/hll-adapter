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
        TODO("Not yet implemented")
    }

    fun add(auth: Authentication, words: List<String>): Any {
        TODO("Not yet implemented")
    }

    fun remove(auth: Authentication, words: List<String>): Any {
        TODO("Not yet implemented")
    }

}
