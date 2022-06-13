package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rcon.RconClient
import de.sambalmueslie.hll.adapter.rcon.RconClientConfig
import de.sambalmueslie.hll.adapter.rest.action.*
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class RconService(
    config: RconClientConfig
) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RconService::class.java)
    }

    private val client = RconClient(config)

    init {
        client.connect()
    }

    private val serverAdminLogAction = ServerAdminLogAction(client)
    fun getAdminLog(auth: Authentication, minutes: Int, filter: String): List<String> {
        return serverAdminLogAction.get(auth, minutes, filter)
    }

    private val currentMapAction = CurrentMapAction(client)
    fun getCurrentMap(auth: Authentication): String {
        return currentMapAction.get(auth)
    }

    private val serverNameAction = ServerNameAction(client)
    fun getServerName(auth: Authentication): String {
        return serverNameAction.get(auth)
    }

    private val serverSlotsAction = ServerSlotsAction(client)
    fun getServerSlots(auth: Authentication): Slots {
        return serverSlotsAction.get(auth)
    }

    fun changeMap(auth: Authentication, name: String): Any {
        return currentMapAction.set(auth, name)
    }

    private val serverMessageAction = ServerMessageAction(client)
    fun setServerMessage(auth: Authentication, message: String): Any {
        return serverMessageAction.set(auth, message)
    }


    private val playerPunishAction = PlayerPunishAction(client)
    fun playerPunish(auth: Authentication, player: String, reason: String): Any {
        return playerPunishAction.punish(auth, player, reason)
    }

    private val playerKickAction = PlayerKickAction(client)
    fun playerKick(auth: Authentication, player: String, reason: String): Any {
        return playerKickAction.kick(auth, player, reason)
    }


    private val serverHighPingAction = HighPingAction(client)
    fun setHighPingThreshold(auth: Authentication, millis: Int): Any {
        return serverHighPingAction.set(auth, millis)
    }

    fun getHighPing(auth: Authentication): Int {
        return serverHighPingAction.get(auth)
    }

    private val temporaryBanAction = TemporaryBanAction(client)
    fun playerBanTempByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String): Any {
        return temporaryBanAction.addByName(auth, name, hours, reason, admin)
    }

    fun playerBanTempBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String): Any {
        return temporaryBanAction.addBySteamId(auth, steamId, hours, reason, admin)
    }

    fun playerBanTempRemove(auth: Authentication, player: String): Any {
        return temporaryBanAction.remove(auth, player)
    }

    fun getTempBans(auth: Authentication): Set<String> {
        return temporaryBanAction.get(auth)
    }

    private val serverPlayerAction = ServerPlayerAction(client)
    fun getPlayerNames(auth: Authentication): Set<String> {
        return serverPlayerAction.get(auth)
    }

    private val manageAdminAction = ManageAdminAction(client)
    fun getAdminIds(auth: Authentication): Set<String> {
        return manageAdminAction.get(auth)
    }

    fun adminAdd(auth: Authentication, steamId: String, group: String, comment: String): Any {
        return manageAdminAction.add(auth, steamId, group, comment)
    }

    fun adminRemove(auth: Authentication, steamId: String): Any {
        return manageAdminAction.remove(auth, steamId)
    }

    fun getAdminGroups(auth: Authentication): Set<String> {
        return manageAdminAction.getGroups(auth)
    }

    private val manageVipAction = ManageVipAction(client)
    fun getVipIds(auth: Authentication): Set<String> {
        return manageVipAction.get(auth)
    }

    fun vipAdd(auth: Authentication, steamId: String, description: String): Any {
        return manageVipAction.add(auth, steamId, description)
    }

    fun vipRemove(auth: Authentication, steamId: String): Any {
        return manageVipAction.remove(auth, steamId)
    }

    fun getNumVipSlots(auth: Authentication): Int {
        return manageVipAction.getSlots(auth)
    }

    fun setNumVipSlots(auth: Authentication, max: Int): Any {
        return manageVipAction.setSlots(auth, max)
    }

    private val mapRotationAction = MapRotationAction(client)
    fun getMapsInRotation(auth: Authentication): Set<String> {
        return mapRotationAction.get(auth)
    }

    fun addMapToRotation(auth: Authentication, name: String): Any {
        return mapRotationAction.add(auth, name)
    }

    fun removeMapFromRotation(auth: Authentication, name: String): Any {
        return mapRotationAction.remove(auth, name)
    }


    private val idleTimeAction = IdleTimeAction(client)
    fun getIdleTime(auth: Authentication): Int {
        return idleTimeAction.get(auth)
    }


    fun setKickIdleTime(auth: Authentication, minutes: Int): Any {
        return idleTimeAction.set(auth, minutes)
    }


    private val voteKickAction = VoteKickAction(client)

    fun isVoteKickEnabled(auth: Authentication): Boolean {
        return voteKickAction.isEnabled(auth)
    }

    fun setVoteKick(auth: Authentication, enabled: Boolean): Any {
        return voteKickAction.setEnabled(auth, enabled)
    }

    fun getVoteKickThreshold(auth: Authentication): Int {
        return voteKickAction.getThreshold(auth)
    }

    fun setVoteKickThreshold(auth: Authentication, threshold: Int): Any {
        return voteKickAction.setThreshold(auth, threshold)
    }

    fun resetVoteKickThreshold(auth: Authentication): Any {
        return voteKickAction.resetThreshold(auth)
    }

    private val permanentBanAction = PermanentBanAction(client)
    fun playerBanPermanentByName(auth: Authentication, name: String, reason: String, admin: String): Any {
        return permanentBanAction.addByName(auth, name, reason, admin)
    }

    fun playerBanPermanentBySteamId(auth: Authentication, steamId: String, reason: String, admin: String): Any {
        return permanentBanAction.addBySteamId(auth, steamId, reason, admin)
    }

    fun playerBanPermanentRemove(auth: Authentication, player: String): Any {
        return permanentBanAction.remove(auth, player)
    }

    fun getPermanentBans(auth: Authentication): Set<String> {
        return permanentBanAction.get(auth)
    }

    private val autoBalanceAction = AutoBalanceAction(client)
    fun getAutoBalanceThreshold(auth: Authentication): Int {
        return autoBalanceAction.getThreshold(auth)
    }

    fun setAutoBalanceThreshold(auth: Authentication, threshold: Int): Any {
        return autoBalanceAction.setThreshold(auth, threshold)
    }

    fun isAutoBalanceEnabled(auth: Authentication): Boolean {
        return autoBalanceAction.isEnabled(auth)
    }

    fun setAutoBalance(auth: Authentication, enabled: Boolean): Any {
        return autoBalanceAction.setEnabled(auth, enabled)
    }

    private val teamSwitchCoolDownAction = TeamSwitchCoolDownAction(client)
    fun getTeamSwitchCoolDown(auth: Authentication): Int {
        return teamSwitchCoolDownAction.get(auth)
    }

    fun setTeamSwitchCoolDown(auth: Authentication, coolDown: Int): Any {
        return teamSwitchCoolDownAction.set(auth, coolDown)
    }

    private val playerInfoAction = PlayerInfoAction(client)
    fun getPlayerInfo(auth: Authentication, player: String): Any {
        return playerInfoAction.get(auth, player)
    }

    private val playerSwitchAction = PlayerSwitchAction(client)
    fun switchPlayerOnDeath(auth: Authentication, player: String): Any {
        return playerSwitchAction.switchOnDeath(auth, player)
    }

    fun switchPlayerImmediately(auth: Authentication, player: String): Any {
        return playerSwitchAction.switchImmediately(auth, player)
    }

    private val maxQueuedPlayersAction = MaxQueuedPlayersAction(client)
    fun setMaxQueuedPlayers(auth: Authentication, max: Int): Any {
        return maxQueuedPlayersAction.set(auth, max)
    }

    fun getMaxQueuedPlayers(auth: Authentication): Int {
        return maxQueuedPlayersAction.get(auth)
    }

    private val broadcastAction = BroadcastAction(client)
    fun broadcast(auth: Authentication, message: String): Any {
        return broadcastAction.broadcast(auth, message)
    }

    private val serverMapsAction = ServerMapsAction(client)
    fun getMaps(auth: Authentication): Set<String> {
        return serverMapsAction.get(auth)
    }

    private val profanityWordsAction = ManageProfanityWordsAction(client)
    fun getProfanityWords(auth: Authentication): Set<String> {
        return profanityWordsAction.get(auth)
    }

    fun profanityWordsAdd(auth: Authentication, words: List<String>): Any {
        return profanityWordsAction.add(auth, words)
    }

    fun profanityWordsRemove(auth: Authentication, word: String): Any {
        return profanityWordsAction.remove(auth, word)
    }


}
