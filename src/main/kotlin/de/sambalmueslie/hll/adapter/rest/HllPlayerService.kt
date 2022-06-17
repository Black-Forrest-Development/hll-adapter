package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.*
import de.sambalmueslie.hll.adapter.rest.error.InvalidConfigurationException
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class HllPlayerService(private val actionService: ActionService) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllPlayerService::class.java)
    }
    fun playerPunish(auth: Authentication, player: String, reason: String) = getPlayerPunishAction().punish(auth, player, reason)
    private fun getPlayerPunishAction(): PlayerPunishAction = actionService.get(PlayerPunishAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerPunishAction")

    fun playerKick(auth: Authentication, player: String, reason: String) = getPlayerKickAction().kick(auth, player, reason)
    private fun getPlayerKickAction(): PlayerKickAction = actionService.get(PlayerKickAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerKickAction")

    fun playerBanTempByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String) = getTemporaryBanAction().addByName(auth, name, hours, reason, admin)
    fun playerBanTempBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String) = getTemporaryBanAction().addBySteamId(auth, steamId, hours, reason, admin)
    fun playerBanTempRemove(auth: Authentication, player: String) = getTemporaryBanAction().remove(auth, player)
    fun getTempBans(auth: Authentication) = getTemporaryBanAction().get(auth)
    private fun getTemporaryBanAction(): TemporaryBanAction = actionService.get(TemporaryBanAction.ID) ?: throw InvalidConfigurationException("Cannot find TemporaryBanAction")

    fun playerBanPermanentByName(auth: Authentication, name: String, reason: String, admin: String) = getPermanentBanAction().addByName(auth, name, reason, admin)
    fun playerBanPermanentBySteamId(auth: Authentication, steamId: String, reason: String, admin: String) = getPermanentBanAction().addBySteamId(auth, steamId,reason, admin)
    fun playerBanPermanentRemove(auth: Authentication, player: String) = getPermanentBanAction().remove(auth, player)
    fun getPermanentBans(auth: Authentication) = getPermanentBanAction().get(auth)
    private fun getPermanentBanAction(): PermanentBanAction = actionService.get(PermanentBanAction.ID) ?: throw InvalidConfigurationException("Cannot find PermanentBanAction")

    fun getPlayerNames(auth: Authentication) = getServerPlayerAction().get(auth)
    private fun getServerPlayerAction(): ServerPlayerAction = actionService.get(ServerPlayerAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerPlayerAction")

    fun switchPlayerOnDeath(auth: Authentication, player: String) = getPlayerSwitchAction().switchOnDeath(auth, player)
    fun switchPlayerImmediately(auth: Authentication, player: String) = getPlayerSwitchAction().switchImmediately(auth, player)
    private fun getPlayerSwitchAction(): PlayerSwitchAction = actionService.get(PlayerSwitchAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerSwitchAction")

    fun getPlayerInfo(auth: Authentication, player: String) = getPlayerInfoAction().get(auth, player)
    private fun getPlayerInfoAction(): PlayerInfoAction = actionService.get(PlayerInfoAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerInfoAction")
}
