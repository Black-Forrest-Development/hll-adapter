package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.*
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllGameplayService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllGameplayService::class.java)
    }

    fun setHighPingThreshold(auth: Authentication, serverId: Long, millis: Int) = getHighPingAction().set(auth,serverId, millis)
    fun getHighPing(auth: Authentication, serverId: Long) = getHighPingAction().get(auth,serverId)
    private fun getHighPingAction(): HighPingAction = actionService.get(HighPingAction.ID) ?: throw InvalidConfigurationException("Cannot find HighPingAction")

    fun getIdleTime(auth: Authentication, serverId: Long) = getIdleTimeAction().get(auth,serverId)
    fun setKickIdleTime(auth: Authentication, serverId: Long, minutes: Int) = getIdleTimeAction().set(auth,serverId, minutes)
    private fun getIdleTimeAction(): IdleTimeAction = actionService.get(IdleTimeAction.ID) ?: throw InvalidConfigurationException("Cannot find IdleTimeAction")

    fun isVoteKickEnabled(auth: Authentication, serverId: Long) = getVoteKickAction().isEnabled(auth,serverId)
    fun setVoteKickEnabled(auth: Authentication, serverId: Long, enabled: Boolean) = getVoteKickAction().setEnabled(auth,serverId, enabled)
    fun getVoteKickThreshold(auth: Authentication, serverId: Long) = getVoteKickAction().getThreshold(auth,serverId)
    fun setVoteKickThreshold(auth: Authentication, serverId: Long, threshold: Int) = getVoteKickAction().setThreshold(auth,serverId, threshold)
    fun resetVoteKickThreshold(auth: Authentication, serverId: Long) = getVoteKickAction().resetThreshold(auth,serverId)
    private fun getVoteKickAction(): VoteKickAction = actionService.get(VoteKickAction.ID) ?: throw InvalidConfigurationException("Cannot find VoteKickAction")

    fun getAutoBalanceThreshold(auth: Authentication, serverId: Long) = getAutoBalanceAction().getThreshold(auth,serverId)
    fun setAutoBalanceThreshold(auth: Authentication, serverId: Long, threshold: Int) = getAutoBalanceAction().setThreshold(auth,serverId, threshold)
    fun isAutoBalanceEnabled(auth: Authentication, serverId: Long) = getAutoBalanceAction().isEnabled(auth,serverId)
    fun setAutoBalanceEnabled(auth: Authentication, serverId: Long, enabled: Boolean) = getAutoBalanceAction().setEnabled(auth,serverId, enabled)
    private fun getAutoBalanceAction(): AutoBalanceAction = actionService.get(AutoBalanceAction.ID) ?: throw InvalidConfigurationException("Cannot find AutoBalanceAction")

    fun getTeamSwitchCoolDown(auth: Authentication, serverId: Long) = getTeamSwitchCoolDownAction().get(auth,serverId)
    fun setTeamSwitchCoolDown(auth: Authentication, serverId: Long, coolDown: Int) = getTeamSwitchCoolDownAction().set(auth,serverId, coolDown)
    private fun getTeamSwitchCoolDownAction(): TeamSwitchCoolDownAction = actionService.get(TeamSwitchCoolDownAction.ID) ?: throw InvalidConfigurationException("Cannot find TeamSwitchCoolDownAction")

    fun setMaxQueuedPlayers(auth: Authentication, serverId: Long, max: Int) = getMaxQueuedPlayersAction().set(auth,serverId, max)
    fun getMaxQueuedPlayers(auth: Authentication, serverId: Long) = getMaxQueuedPlayersAction().get(auth,serverId)
    private fun getMaxQueuedPlayersAction(): MaxQueuedPlayersAction = actionService.get(MaxQueuedPlayersAction.ID) ?: throw InvalidConfigurationException("Cannot find MaxQueuedPlayersAction")
}
