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

    fun getMapsInRotation(auth: Authentication) = getMapRotationAction().get(auth)

    fun addMapToRotation(auth: Authentication, name: String) = getMapRotationAction().add(auth, name)

    fun removeMapFromRotation(auth: Authentication, name: String) = getMapRotationAction().remove(auth, name)

    private fun getMapRotationAction(): MapRotationAction = actionService.get(MapRotationAction.ID) ?: throw InvalidConfigurationException("Cannot find MapRotationAction")

    fun getCurrentMap(auth: Authentication) = getCurrentMapAction().get(auth)
    fun setCurrentMap(auth: Authentication, name: String) = getCurrentMapAction().set(auth, name)

    private fun getCurrentMapAction(): CurrentMapAction = actionService.get(CurrentMapAction.ID) ?: throw InvalidConfigurationException("Cannot find CurrentMapAction")

    fun getMaps(auth: Authentication) = getServerMapsAction().get(auth)

    private fun getServerMapsAction(): ServerMapsAction = actionService.get(ServerMapsAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerMapsAction")
}
