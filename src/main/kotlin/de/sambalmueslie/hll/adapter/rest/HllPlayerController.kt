package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllPlayerAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/player")
@Tag(name = "Player API")
class HllPlayerController(private val service: HllPlayerService) : HllPlayerAPI {

    @Get("/bans/temporary")
    override fun getTempBans(auth: Authentication): Set<String> = service.getTempBans(auth)

    @Post("/bans/temporary/by/name")
    override fun playerBanTempByName(auth: Authentication, @QueryValue() name: String, @QueryValue() hours: Int, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanTempByName(auth, name, hours, reason, admin)

    @Post("/bans/temporary/by/steam/{steamId}")
    override fun playerBanTempBySteamId(auth: Authentication, @PathVariable steamId: String, @QueryValue() hours: Int, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanTempBySteamId(auth, steamId, hours, reason, admin)

    @Delete("/bans/temporary")
    override fun playerBanTempRemove(auth: Authentication, @QueryValue player: String) = service.playerBanTempRemove(auth, player)

    @Get("/bans/permanent")
    override fun getPermanentBans(auth: Authentication): Set<String> = service.getPermanentBans(auth)

    @Post("/bans/permanent/by/name")
    override fun playerBanPermanentByName(auth: Authentication, @QueryValue() name: String, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanPermanentByName(auth, name, reason, admin)

    @Post("/bans/permanent/by/steam/{steamId}")
    override fun playerBanPermanentBySteamId(auth: Authentication, @PathVariable steamId: String, @QueryValue() reason: String, @QueryValue() admin: String) =
        service.playerBanPermanentBySteamId(auth, steamId, reason, admin)

    @Delete("/bans/permanent")
    override fun playerBanPermanentRemove(auth: Authentication, @QueryValue player: String) = service.playerBanPermanentRemove(auth, player)

    @Post("/punish")
    override fun playerPunish(auth: Authentication, @QueryValue player: String, @QueryValue() reason: String) = service.playerPunish(auth, player, reason)

    @Post("/kick")
    override fun playerKick(auth: Authentication, @QueryValue player: String, @QueryValue() reason: String) = service.playerKick(auth, player, reason)

    @Get()
    override fun getPlayerNames(auth: Authentication): Set<String> = service.getPlayerNames(auth)

    @Get("/info")
    override fun getPlayerInfo(auth: Authentication, @QueryValue player: String) = service.getPlayerInfo(auth, player)

    @Post("/switch/on-death")
    override fun switchPlayerOnDeath(auth: Authentication, @QueryValue player: String) = service.switchPlayerOnDeath(auth, player)

    @Post("/switch/immediately")
    override fun switchPlayerImmediately(auth: Authentication, @QueryValue player: String) = service.switchPlayerImmediately(auth, player)

}
