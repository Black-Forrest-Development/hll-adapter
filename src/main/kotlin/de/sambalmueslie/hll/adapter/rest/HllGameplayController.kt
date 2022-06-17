package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllGameplayAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/gameplay")
@Tag(name = "Gameplay API")
class HllGameplayController(private val service: HllGameplayService) : HllGameplayAPI {

    @Get("/team-switch/cool-down")
    override fun getTeamSwitchCoolDown(auth: Authentication): Int = service.getTeamSwitchCoolDown(auth)

    @Put("/team-switch/cool-down")
    override fun setTeamSwitchCoolDown(auth: Authentication, @QueryValue() coolDown: Int) = service.setTeamSwitchCoolDown(auth, coolDown)

    @Get("/auto-balance/enabled")
    override fun isAutoBalanceEnabled(auth: Authentication): Boolean = service.isAutoBalanceEnabled(auth)

    @Put("/auto-balance/enabled")
    override fun setAutoBalanceEnabled(auth: Authentication, @QueryValue() enabled: Boolean) = service.setAutoBalanceEnabled(auth, enabled)

    @Get("/auto-balance/threshold")
    override fun getAutoBalanceThreshold(auth: Authentication): Int = service.getAutoBalanceThreshold(auth)

    @Put("/auto-balance/threshold")
    override fun setAutoBalanceThreshold(auth: Authentication, @QueryValue() threshold: Int) = service.setAutoBalanceThreshold(auth, threshold)

    @Get("/idle-time")
    override fun getKickIdleTime(auth: Authentication): Int = service.getIdleTime(auth)

    @Put("/idle-time")
    override fun setKickIdleTime(auth: Authentication, @QueryValue() minutes: Int) = service.setKickIdleTime(auth, minutes)

    @Get("/high-ping")
    override fun getHighPingThreshold(auth: Authentication): Int = service.getHighPing(auth)

    @Put("/high-ping")
    override fun setHighPingThreshold(auth: Authentication, @QueryValue() millis: Int) = service.setHighPingThreshold(auth, millis)

    @Get("/max-queued-players")
    override fun getMaxQueuedPlayers(auth: Authentication): Int = service.getMaxQueuedPlayers(auth)

    @Put("/max-queued-players")
    override fun setMaxQueuedPlayers(auth: Authentication, @QueryValue() max: Int) = service.setMaxQueuedPlayers(auth, max)

    @Get("/vote-kick/enabled")
    override fun isVoteKickEnabled(auth: Authentication): Boolean = service.isVoteKickEnabled(auth)

    @Put("/vote-kick/enabled")
    override fun setVoteKickEnabled(auth: Authentication, @QueryValue() enabled: Boolean) = service.setVoteKickEnabled(auth, enabled)

    @Get("/vote-kick/threshold")
    override fun getVoteKickThreshold(auth: Authentication): Int = service.getVoteKickThreshold(auth)

    @Put("/vote-kick/threshold")
    override fun setVoteKickThreshold(auth: Authentication, @QueryValue() threshold: Int) = service.setVoteKickThreshold(auth, threshold)

    @Delete("/vote-kick/threshold")
    override fun resetVoteKickThreshold(auth: Authentication) = service.resetVoteKickThreshold(auth)

}
