package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.Action
import de.sambalmueslie.hll.adapter.rcon.RconClient
import de.sambalmueslie.hll.adapter.rcon.RconClientConfig
import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rest.api.ConnectRequest
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class ActionService(private val clientService: RconClientService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ActionService::class.java)
    }

    private val client = RconClient()
    private val actions = mutableMapOf<String, Action>()

    @Deprecated("intermediate function to connect")
    fun connect(request: ConnectRequest): Any {
        return client.connect(RconClientConfig(request.host, request.port, request.password))
    }

    init {
        register(AutoBalanceAction(clientService))
        register(BroadcastAction(clientService))
        register(CurrentMapAction(clientService))
        register(HighPingAction(clientService))
        register(IdleTimeAction(clientService))
        register(ManageAdminAction(clientService))
        register(ManageProfanityWordsAction(clientService))
        register(ManageVipAction(clientService))
        register(MapRotationAction(clientService))
        register(MaxQueuedPlayersAction(clientService))
        register(PermanentBanAction(clientService))
        register(PlayerInfoAction(clientService))
        register(PlayerKickAction(clientService))
        register(PlayerPunishAction(clientService))
        register(ServerAdminLogAction(clientService))
        register(ServerMapsAction(clientService))
        register(ServerNameAction(clientService))
        register(ServerPlayerAction(clientService))
        register(ServerSlotsAction(clientService))
        register(TeamSwitchCoolDownAction(clientService))
        register(TemporaryBanAction(clientService))
        register(VoteKickAction(clientService))
    }

    private fun register(action: Action) {
        logger.debug("Register action ${action.getId()}")
        actions[action.getId()] = action
    }

    fun getAction(id: String): Action? = actions[id]

    @Suppress("UNCHECKED_CAST")
    fun <T : Action> get(id: String): T? = actions[id] as T?
}
