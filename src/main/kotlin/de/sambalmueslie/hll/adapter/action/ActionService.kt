package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.Action
import de.sambalmueslie.hll.adapter.rcon.RconClientService
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class ActionService(clientService: RconClientService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ActionService::class.java)
    }

    private val actions = mutableMapOf<String, Action>()

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


    @Suppress("UNCHECKED_CAST")
    fun <T : Action> get(id: String): T? = actions[id] as T?
}
