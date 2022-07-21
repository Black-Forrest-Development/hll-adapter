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
    fun playerPunish(auth: Authentication, serverId: Long, player: String, reason: String) = getPlayerPunishAction().punish(auth,serverId, player, reason)
    private fun getPlayerPunishAction(): PlayerPunishAction = actionService.get(PlayerPunishAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerPunishAction")

    fun playerKick(auth: Authentication, serverId: Long, player: String, reason: String) = getPlayerKickAction().kick(auth,serverId, player, reason)
    private fun getPlayerKickAction(): PlayerKickAction = actionService.get(PlayerKickAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerKickAction")

    fun playerBanTempByName(auth: Authentication, serverId: Long, name: String, hours: Int, reason: String, admin: String) = getTemporaryBanAction().addByName(auth,serverId, name, hours, reason, admin)
    fun playerBanTempBySteamId(auth: Authentication, serverId: Long, steamId: String, hours: Int, reason: String, admin: String) = getTemporaryBanAction().addBySteamId(auth,serverId, steamId, hours, reason, admin)
    fun playerBanTempRemove(auth: Authentication, serverId: Long, player: String) = getTemporaryBanAction().remove(auth,serverId, player)
    fun getTempBans(auth: Authentication, serverId: Long) = getTemporaryBanAction().get(auth,serverId)
    private fun getTemporaryBanAction(): TemporaryBanAction = actionService.get(TemporaryBanAction.ID) ?: throw InvalidConfigurationException("Cannot find TemporaryBanAction")

    fun playerBanPermanentByName(auth: Authentication, serverId: Long, name: String, reason: String, admin: String) = getPermanentBanAction().addByName(auth,serverId, name, reason, admin)
    fun playerBanPermanentBySteamId(auth: Authentication, serverId: Long, steamId: String, reason: String, admin: String) = getPermanentBanAction().addBySteamId(auth,serverId, steamId,reason, admin)
    fun playerBanPermanentRemove(auth: Authentication, serverId: Long, player: String) = getPermanentBanAction().remove(auth,serverId, player)
    fun getPermanentBans(auth: Authentication, serverId: Long) = getPermanentBanAction().get(auth,serverId)
    private fun getPermanentBanAction(): PermanentBanAction = actionService.get(PermanentBanAction.ID) ?: throw InvalidConfigurationException("Cannot find PermanentBanAction")

    fun getPlayerNames(auth: Authentication, serverId: Long) = getServerPlayerAction().get(auth,serverId)
    private fun getServerPlayerAction(): ServerPlayerAction = actionService.get(ServerPlayerAction.ID) ?: throw InvalidConfigurationException("Cannot find ServerPlayerAction")

    fun switchPlayerOnDeath(auth: Authentication, serverId: Long, player: String) = getPlayerSwitchAction().switchOnDeath(auth,serverId, player)
    fun switchPlayerImmediately(auth: Authentication, serverId: Long, player: String) = getPlayerSwitchAction().switchImmediately(auth,serverId, player)
    private fun getPlayerSwitchAction(): PlayerSwitchAction = actionService.get(PlayerSwitchAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerSwitchAction")

    fun getPlayerInfo(auth: Authentication, serverId: Long, player: String) = getPlayerInfoAction().get(auth,serverId, player)
    private fun getPlayerInfoAction(): PlayerInfoAction = actionService.get(PlayerInfoAction.ID) ?: throw InvalidConfigurationException("Cannot find PlayerInfoAction")
}
