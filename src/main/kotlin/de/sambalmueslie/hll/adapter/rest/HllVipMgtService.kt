package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.ActionService
import de.sambalmueslie.hll.adapter.action.ManageVipAction
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllVipMgtService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllVipMgtService::class.java)
    }
    fun getVipIds(auth: Authentication) = getManageVipAction().get(auth)

    fun vipAdd(auth: Authentication, steamId: String, description: String) = getManageVipAction().add(auth, steamId, description)

    fun vipRemove(auth: Authentication, steamId: String) = getManageVipAction().remove(auth, steamId)

    fun getNumVipSlots(auth: Authentication) = getManageVipAction().getSlots(auth)

    fun setNumVipSlots(auth: Authentication, max: Int) = getManageVipAction().setSlots(auth, max)

    private fun getManageVipAction(): ManageVipAction = actionService.get(ManageVipAction.ID) ?: throw InvalidConfigurationException("Cannot find ManageVipAction")
}
