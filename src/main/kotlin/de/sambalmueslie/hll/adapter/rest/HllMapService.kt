package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.ActionService
import de.sambalmueslie.hll.adapter.action.CurrentMapAction
import de.sambalmueslie.hll.adapter.action.MapRotationAction
import de.sambalmueslie.hll.adapter.action.ServerMapsAction
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllMapService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllMapService::class.java)
    }

    fun getMapsInRotation(auth: Authentication, serverId: Long) = getMapRotationAction().get(auth,serverId)

    fun addMapToRotation(auth: Authentication, serverId: Long, name: String) = getMapRotationAction().add(auth,serverId, name)

    fun removeMapFromRotation(auth: Authentication, serverId: Long, name: String) = getMapRotationAction().remove(auth,serverId, name)

    private fun getMapRotationAction(): MapRotationAction = actionService.get(MapRotationAction.ID) ?: throw InvalidConfigurationException("Cannot find MapRotationAction")

    fun getCurrentMap(auth: Authentication, serverId: Long) = getCurrentMapAction().get(auth,serverId)
    fun setCurrentMap(auth: Authentication, serverId: Long, name: String) = getCurrentMapAction().set(auth,serverId, name)

    private fun getCurrentMapAction(): CurrentMapAction = actionService.get(CurrentMapAction.ID) ?: throw InvalidConfigurationException("Cannot find CurrentMapAction")

    fun getMaps(auth: Authentication, serverId: Long) = getServerMapsAction().get(auth,serverId)

    private fun getServerMapsAction(): ServerMapsAction = actionService.get(ServerMapsAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerMapsAction")
}
