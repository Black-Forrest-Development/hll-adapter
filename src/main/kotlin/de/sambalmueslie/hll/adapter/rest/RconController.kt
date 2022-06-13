package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("api/rcon")
class RconController(private val service: RconService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconController::class.java)
    }

    // admin log
    fun getAdminLog(auth: Authentication, minutes: Int, filter: String = ""): List<String> = service.getAdminLog(auth,minutes, filter)

    // getter
    @Get("/server/name")
    fun getServerName(auth: Authentication): String = service.getServerName(auth)

    @Get("/server/map")
    fun getCurrentMap(auth: Authentication): String = service.getCurrentMap(auth)

    @Get("/server/slots")
    fun getServerSlots(auth: Authentication): Slots = service.getServerSlots(auth)

    @Get("/server/players")
    fun getPlayerNames(auth: Authentication): Set<String> = service.getPlayerNames(auth)

    @Get("/server/admins")
    fun getAdminIds(auth: Authentication): Set<String> = service.getAdminIds(auth)

    @Get("/server/vips")
    fun getVipIds(auth: Authentication): Set<String> = service.getVipIds(auth)

    @Get("/server/admin/groups")
    fun getAdminGroups(auth: Authentication): Set<String> = service.getAdminGroups(auth)

    @Get("/server/bans/temp")
    fun getTempBans(auth: Authentication): Set<String> = service.getTempBans(auth)

    @Get("/server/bans/permanent")
    fun getPermanentBans(auth: Authentication): Set<String> = service.getPermanentBans(auth)

    @Get("/server/maps")
    fun getMaps(auth: Authentication): Set<String> = service.getMaps(auth)

    @Get("/server/teamswitch/cooldown")
    fun getTeamSwitchCoolDown(auth: Authentication): Int = service.getTeamSwitchCoolDown(auth)

    @Get("/server/autobalance")
    fun isAutoBalanceEnabled(auth: Authentication): Boolean = service.isAutoBalanceEnabled(auth)

    @Get("/server/autobalance/threshold")
    fun getAutoBalanceThreshold(auth: Authentication): Int = service.getAutoBalanceThreshold(auth)

    @Get("/server/idletime")
    fun getIdleTime(auth: Authentication): Int = service.getIdleTime(auth)

    @Get("/server/highping")
    fun getHighPing(auth: Authentication): Int = service.getHighPing(auth)

    @Get("/server/maxqueuedplayers")
    fun getMaxQueuedPlayers(auth: Authentication): Int = service.getMaxQueuedPlayers(auth)

    @Get("/server/vips/slots")
    fun getNumVipSlots(auth: Authentication): Int = service.getNumVipSlots(auth)

    @Get("/server/profanity")
    fun getProfanityWords(auth: Authentication): Set<String> = service.getProfanityWords(auth)

    @Get("/server/votekick")
    fun isVoteKickEnabled(auth: Authentication): Boolean = service.isVoteKickEnabled(auth)

    @Get("/server/votekick/threshold")
    fun getVoteKickThreshold(auth: Authentication): Int = service.getVoteKickThreshold(auth)


    // server handling
    fun setServerMessage(auth: Authentication, message: String) = service.setServerMessage(auth, message)
    fun setAutoBalance(auth: Authentication, enabled: Boolean) = service.setAutoBalance(auth, enabled)
    fun setAutoBalanceThreshold(auth: Authentication, threshold: Int) = service.setAutoBalanceThreshold(auth, threshold)
    fun setTeamSwitchCoolDown(auth: Authentication, coolDown: Int) = service.setTeamSwitchCoolDown(auth, coolDown)
    fun setMaxQueuedPlayers(auth: Authentication, max: Int) = service.setMaxQueuedPlayers(auth, max)
    fun setNumVipSlots(auth: Authentication, max: Int) = service.setNumVipSlots(auth, max)
    fun broadcast(auth: Authentication, message: String) = service.broadcast(auth, message)

    // admin handling
    fun adminAdd(auth: Authentication, steamId: String, group: String, comment: String = "") = service.adminAdd(auth, steamId, group, comment)
    fun adminRemove(auth: Authentication, steamId: String) = service.adminRemove(auth, steamId)

    // vip handling
    fun vipAdd(auth: Authentication, steamId: String, description: String = "") = service.vipAdd(auth, steamId, description)
    fun vipRemove(auth: Authentication, steamId: String) = service.vipRemove(auth, steamId)

    // map handling
    fun changeMap(auth: Authentication, name: String) = service.changeMap(auth, name)
    fun getMapsInRotation(auth: Authentication): Set<String> = service.getMapsInRotation(auth)
    fun addMapToRotation(auth: Authentication, name: String) = service.addMapToRotation(auth, name)
    fun removeMapFromRotation(auth: Authentication, name: String) = service.removeMapFromRotation(auth, name)

    // player handling
    fun playerPunish(auth: Authentication, player: String, reason: String) = service.playerPunish(auth, player, reason)
    fun playerKick(auth: Authentication, player: String, reason: String) = service.playerKick(auth, player, reason)
    fun setKickIdleTime(auth: Authentication, minutes: Int) = service.setKickIdleTime(auth, minutes)
    fun setHighPingThreshold(auth: Authentication, millis: Int) = service.setHighPingThreshold(auth, millis)
    fun playerBanTempByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String) = service.playerBanTempByName(auth, name, hours, reason, admin)
    fun playerBanTempBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String) = service.playerBanTempBySteamId(auth, steamId, hours, reason, admin)
    fun playerBanTempRemove(auth: Authentication, player: String) = service.playerBanTempRemove(auth, player)

    fun playerBanPermanentByName(auth: Authentication, name: String, reason: String, admin: String) = service.playerBanPermanentByName(auth, name, reason, admin)
    fun playerBanPermanentBySteamId(auth: Authentication, steamId: String, reason: String, admin: String) = service.playerBanPermanentBySteamId(auth, steamId, reason, admin)
    fun playerBanPermanentRemove(auth: Authentication, player: String) = service.playerBanPermanentRemove(auth, player)

    fun getPlayerInfo(auth: Authentication, player: String) = service.getPlayerInfo(auth, player)
    fun switchPlayerOnDeath(auth: Authentication, player: String) = service.switchPlayerOnDeath(auth, player)
    fun switchPlayerImmediately(auth: Authentication, player: String) = service.switchPlayerImmediately(auth, player)

    // profanity
    fun profanityWordsAdd(auth: Authentication, words: List<String>) = service.profanityWordsAdd(auth, words)
    fun profanityWordsRemove(auth: Authentication, words: List<String>) = service.profanityWordsRemove(auth, words)

    // vote kick
    fun setVoteKick(auth: Authentication, enabled: Boolean) = service.setVoteKick(auth, enabled)
    fun setVoteKickThreshold(auth: Authentication, threshold: Int) = service.setVoteKickThreshold(auth, threshold)
    fun resetVoteKickThreshold(auth: Authentication) = service.resetVoteKickThreshold(auth)


}
