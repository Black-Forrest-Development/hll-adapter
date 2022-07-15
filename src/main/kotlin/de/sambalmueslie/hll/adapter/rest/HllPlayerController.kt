package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllPlayerAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/player")
@Tag(name = "Player API")
class HllPlayerController(private val service: HllPlayerService) : HllPlayerAPI {

    @Get("/{serverId}/bans/temporary")
    override fun getTempBans(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getTempBans(auth,serverId)

    @Post("/{serverId}/bans/temporary/by/name")
    override fun playerBanTempByName(auth: Authentication, @PathVariable serverId: Long, @QueryValue() name: String, @QueryValue() hours: Int, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanTempByName(auth,serverId, name, hours, reason, admin)

    @Post("/{serverId}/bans/temporary/by/steam/{steamId}")
    override fun playerBanTempBySteamId(auth: Authentication, @PathVariable serverId: Long, @PathVariable steamId: String, @QueryValue() hours: Int, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanTempBySteamId(auth,serverId, steamId, hours, reason, admin)

    @Delete("/{serverId}/bans/temporary")
    override fun playerBanTempRemove(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String) = service.playerBanTempRemove(auth,serverId, player)

    @Get("/{serverId}/bans/permanent")
    override fun getPermanentBans(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getPermanentBans(auth,serverId)

    @Post("/{serverId}/bans/permanent/by/name")
    override fun playerBanPermanentByName(auth: Authentication, @PathVariable serverId: Long, @QueryValue() name: String, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanPermanentByName(auth,serverId, name, reason, admin)

    @Post("/{serverId}/bans/permanent/by/steam/{steamId}")
    override fun playerBanPermanentBySteamId(auth: Authentication, @PathVariable serverId: Long, @PathVariable steamId: String, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanPermanentBySteamId(auth,serverId, steamId, reason, admin)

    @Delete("/{serverId}/bans/permanent")
    override fun playerBanPermanentRemove(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String) = service.playerBanPermanentRemove(auth,serverId, player)

    @Post("/{serverId}/punish")
    override fun playerPunish(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String, @QueryValue() reason: String) = service.playerPunish(auth,serverId, player, reason)

    @Post("/{serverId}/kick")
    override fun playerKick(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String, @QueryValue() reason: String) = service.playerKick(auth,serverId, player, reason)

    @Get("/{serverId}")
    override fun getPlayerNames(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getPlayerNames(auth,serverId)

    @Get("/{serverId}/info")
    override fun getPlayerInfo(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String) = service.getPlayerInfo(auth,serverId, player)

    @Post("/{serverId}/switch/on-death")
    override fun switchPlayerOnDeath(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String) = service.switchPlayerOnDeath(auth,serverId, player)

    @Post("/{serverId}/switch/immediately")
    override fun switchPlayerImmediately(auth: Authentication, @PathVariable serverId: Long, @QueryValue player: String) = service.switchPlayerImmediately(auth,serverId, player)

}
