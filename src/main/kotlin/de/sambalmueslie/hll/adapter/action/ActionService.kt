package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.Action
import de.sambalmueslie.hll.adapter.rcon.RconClient
import de.sambalmueslie.hll.adapter.rcon.RconClientConfig
import de.sambalmueslie.hll.adapter.rest.api.ConnectRequest
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class ActionService {

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
        register(AutoBalanceAction(client))
        register(BroadcastAction(client))
        register(CurrentMapAction(client))
        register(HighPingAction(client))
        register(IdleTimeAction(client))
        register(ManageAdminAction(client))
        register(ManageProfanityWordsAction(client))
        register(ManageVipAction(client))
        register(MapRotationAction(client))
        register(MaxQueuedPlayersAction(client))
        register(PermanentBanAction(client))
        register(PlayerInfoAction(client))
        register(PlayerKickAction(client))
        register(PlayerPunishAction(client))
        register(ServerAdminLogAction(client))
        register(ServerMapsAction(client))
        register(ServerNameAction(client))
        register(ServerPlayerAction(client))
        register(ServerSlotsAction(client))
        register(TeamSwitchCoolDownAction(client))
        register(TemporaryBanAction(client))
        register(VoteKickAction(client))
    }

    private fun register(action: Action) {
        logger.debug("Register action ${action.getId()}")
        actions[action.getId()] = action
    }

    fun getAction(id: String): Action? = actions[id]

    @Suppress("UNCHECKED_CAST")
    fun <T : Action> get(id: String): T? = actions[id] as T?
}
