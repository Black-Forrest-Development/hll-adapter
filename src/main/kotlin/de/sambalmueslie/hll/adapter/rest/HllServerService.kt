package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.*
import de.sambalmueslie.hll.adapter.rest.api.Message
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllServerService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllServerService::class.java)
    }

    fun getAdminLog(auth: Authentication, serverId: Long, minutes: Int, filter: String) = getServerAdminLogAction().get(auth,serverId, minutes, filter)
    private fun getServerAdminLogAction(): ServerAdminLogAction = actionService.get(ServerAdminLogAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerAdminLogAction")

    fun getServerName(auth: Authentication, serverId: Long) = getServerNameAction().get(auth,serverId)
    private fun getServerNameAction(): ServerNameAction = actionService.get(ServerNameAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerNameAction")

    fun getServerSlots(auth: Authentication, serverId: Long) = getServerSlotsAction().get(auth,serverId)
    private fun getServerSlotsAction(): ServerSlotsAction = actionService.get(ServerSlotsAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerSlotsAction")

    fun setServerMessage(auth: Authentication, serverId: Long, message: Message) = getServerMessageAction().set(auth,serverId, message)
    private fun getServerMessageAction(): ServerMessageAction = actionService.get(ServerMessageAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerMessageAction")

    fun broadcast(auth: Authentication, serverId: Long, message: Message) = getBroadcastAction().broadcast(auth,serverId, message)
    private fun getBroadcastAction(): BroadcastAction = actionService.get(BroadcastAction.ID) ?: throw InvalidConfigurationException("Cannot find BroadcastAction")

}
