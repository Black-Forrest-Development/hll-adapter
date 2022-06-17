package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.ActionService
import de.sambalmueslie.hll.adapter.action.ManageAdminAction
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllAdminMgtService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllAdminMgtService::class.java)
    }

    fun getAdminGroups(auth: Authentication) = getManageAdminAction().getGroups(auth)
    fun getAdminIds(auth: Authentication) = getManageAdminAction().get(auth)
    fun adminAdd(auth: Authentication, steamId: String, group: String, comment: String) = getManageAdminAction().add(auth, steamId, group, comment)
    fun adminRemove(auth: Authentication, steamId: String) = getManageAdminAction().remove(auth, steamId)

    private fun getManageAdminAction(): ManageAdminAction = actionService.get(ManageAdminAction.ID) ?: throw InvalidConfigurationException("Cannot find ManageAdminAction")
}
