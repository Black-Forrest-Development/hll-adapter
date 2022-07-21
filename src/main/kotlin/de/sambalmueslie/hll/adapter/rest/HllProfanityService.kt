package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.ActionService
import de.sambalmueslie.hll.adapter.action.ManageProfanityWordsAction
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllProfanityService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllProfanityService::class.java)
    }
    fun getProfanityWords(auth: Authentication, serverId: Long) = getManageProfanityWordsAction().get(auth,serverId)

    fun profanityWordsAdd(auth: Authentication, serverId: Long, words: List<String>) = getManageProfanityWordsAction().add(auth,serverId, words)

    fun profanityWordsRemove(auth: Authentication, serverId: Long, word: String) = getManageProfanityWordsAction().remove(auth,serverId, word)

    private fun getManageProfanityWordsAction(): ManageProfanityWordsAction = actionService.get(ManageProfanityWordsAction.ID) ?: throw InvalidConfigurationException("Cannot find ManageProfanityWordsAction")
}
