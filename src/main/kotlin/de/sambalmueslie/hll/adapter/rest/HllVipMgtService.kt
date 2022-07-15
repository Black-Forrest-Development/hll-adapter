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

    fun getVipIds(auth: Authentication, serverId: Long) = getManageVipAction().get(auth, serverId)

    fun vipAdd(auth: Authentication, serverId: Long, steamId: String, description: String) = getManageVipAction().add(auth, serverId, steamId, description)

    fun vipRemove(auth: Authentication, serverId: Long, steamId: String) = getManageVipAction().remove(auth, serverId, steamId)

    fun getNumVipSlots(auth: Authentication, serverId: Long) = getManageVipAction().getSlots(auth, serverId)

    fun setNumVipSlots(auth: Authentication, serverId: Long, max: Int) = getManageVipAction().setSlots(auth, serverId, max)

    private fun getManageVipAction(): ManageVipAction = actionService.get(ManageVipAction.ID) ?: throw InvalidConfigurationException("Cannot find ManageVipAction")
}
