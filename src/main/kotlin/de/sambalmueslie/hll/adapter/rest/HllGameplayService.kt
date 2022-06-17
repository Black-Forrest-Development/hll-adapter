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

    fun setHighPingThreshold(auth: Authentication, millis: Int) = getHighPingAction().set(auth, millis)
    fun getHighPing(auth: Authentication) = getHighPingAction().get(auth)
    private fun getHighPingAction(): HighPingAction = actionService.get(HighPingAction.ID) ?: throw InvalidConfigurationException("Cannot find HighPingAction")

    fun getIdleTime(auth: Authentication) = getIdleTimeAction().get(auth)
    fun setKickIdleTime(auth: Authentication, minutes: Int) = getIdleTimeAction().set(auth, minutes)
    private fun getIdleTimeAction(): IdleTimeAction = actionService.get(IdleTimeAction.ID) ?: throw InvalidConfigurationException("Cannot find IdleTimeAction")

    fun isVoteKickEnabled(auth: Authentication) = getVoteKickAction().isEnabled(auth)
    fun setVoteKickEnabled(auth: Authentication, enabled: Boolean) = getVoteKickAction().setEnabled(auth, enabled)
    fun getVoteKickThreshold(auth: Authentication) = getVoteKickAction().getThreshold(auth)
    fun setVoteKickThreshold(auth: Authentication, threshold: Int) = getVoteKickAction().setThreshold(auth, threshold)
    fun resetVoteKickThreshold(auth: Authentication) = getVoteKickAction().resetThreshold(auth)
    private fun getVoteKickAction(): VoteKickAction = actionService.get(VoteKickAction.ID) ?: throw InvalidConfigurationException("Cannot find VoteKickAction")

    fun getAutoBalanceThreshold(auth: Authentication) = getAutoBalanceAction().getThreshold(auth)
    fun setAutoBalanceThreshold(auth: Authentication, threshold: Int) = getAutoBalanceAction().setThreshold(auth, threshold)
    fun isAutoBalanceEnabled(auth: Authentication) = getAutoBalanceAction().isEnabled(auth)
    fun setAutoBalanceEnabled(auth: Authentication, enabled: Boolean) = getAutoBalanceAction().setEnabled(auth, enabled)
    private fun getAutoBalanceAction(): AutoBalanceAction = actionService.get(AutoBalanceAction.ID) ?: throw InvalidConfigurationException("Cannot find AutoBalanceAction")

    fun getTeamSwitchCoolDown(auth: Authentication) = getTeamSwitchCoolDownAction().get(auth)
    fun setTeamSwitchCoolDown(auth: Authentication, coolDown: Int) = getTeamSwitchCoolDownAction().set(auth, coolDown)
    private fun getTeamSwitchCoolDownAction(): TeamSwitchCoolDownAction = actionService.get(TeamSwitchCoolDownAction.ID) ?: throw InvalidConfigurationException("Cannot find TeamSwitchCoolDownAction")

    fun setMaxQueuedPlayers(auth: Authentication, max: Int) = getMaxQueuedPlayersAction().set(auth, max)
    fun getMaxQueuedPlayers(auth: Authentication) = getMaxQueuedPlayersAction().get(auth)
    private fun getMaxQueuedPlayersAction(): MaxQueuedPlayersAction = actionService.get(MaxQueuedPlayersAction.ID) ?: throw InvalidConfigurationException("Cannot find MaxQueuedPlayersAction")
}
