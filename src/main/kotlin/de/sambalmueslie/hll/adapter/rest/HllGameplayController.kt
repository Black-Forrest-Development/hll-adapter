package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllGameplayAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/gameplay")
@Tag(name = "Gameplay API")
class HllGameplayController(private val service: HllGameplayService) : HllGameplayAPI {

    @Get("/{serverId}/team-switch/cool-down")
    override fun getTeamSwitchCoolDown(auth: Authentication, @PathVariable serverId: Long): Int = service.getTeamSwitchCoolDown(auth,serverId)

    @Put("/{serverId}/team-switch/cool-down")
    override fun setTeamSwitchCoolDown(auth: Authentication, @PathVariable serverId: Long, @QueryValue() coolDown: Int) = service.setTeamSwitchCoolDown(auth,serverId, coolDown)

    @Get("/{serverId}/auto-balance/enabled")
    override fun isAutoBalanceEnabled(auth: Authentication, @PathVariable serverId: Long): Boolean = service.isAutoBalanceEnabled(auth,serverId)

    @Put("/{serverId}/auto-balance/enabled")
    override fun setAutoBalanceEnabled(auth: Authentication, @PathVariable serverId: Long, @QueryValue() enabled: Boolean) = service.setAutoBalanceEnabled(auth,serverId, enabled)

    @Get("/{serverId}/auto-balance/threshold")
    override fun getAutoBalanceThreshold(auth: Authentication, @PathVariable serverId: Long): Int = service.getAutoBalanceThreshold(auth,serverId)

    @Put("/{serverId}/auto-balance/threshold")
    override fun setAutoBalanceThreshold(auth: Authentication, @PathVariable serverId: Long, @QueryValue() threshold: Int) = service.setAutoBalanceThreshold(auth,serverId, threshold)

    @Get("/{serverId}/idle-time")
    override fun getKickIdleTime(auth: Authentication, @PathVariable serverId: Long): Int = service.getIdleTime(auth,serverId)

    @Put("/{serverId}/idle-time")
    override fun setKickIdleTime(auth: Authentication, @PathVariable serverId: Long, @QueryValue() minutes: Int) = service.setKickIdleTime(auth,serverId, minutes)

    @Get("/{serverId}/high-ping")
    override fun getHighPingThreshold(auth: Authentication, @PathVariable serverId: Long): Int = service.getHighPing(auth,serverId)

    @Put("/{serverId}/high-ping")
    override fun setHighPingThreshold(auth: Authentication, @PathVariable serverId: Long, @QueryValue() millis: Int) = service.setHighPingThreshold(auth,serverId, millis)

    @Get("/{serverId}/max-queued-players")
    override fun getMaxQueuedPlayers(auth: Authentication, @PathVariable serverId: Long): Int = service.getMaxQueuedPlayers(auth,serverId)

    @Put("/{serverId}/max-queued-players")
    override fun setMaxQueuedPlayers(auth: Authentication, @PathVariable serverId: Long, @QueryValue() max: Int) = service.setMaxQueuedPlayers(auth,serverId, max)

    @Get("/{serverId}/vote-kick/enabled")
    override fun isVoteKickEnabled(auth: Authentication, @PathVariable serverId: Long): Boolean = service.isVoteKickEnabled(auth,serverId)

    @Put("/{serverId}/vote-kick/enabled")
    override fun setVoteKickEnabled(auth: Authentication, @PathVariable serverId: Long, @QueryValue() enabled: Boolean) = service.setVoteKickEnabled(auth,serverId, enabled)

    @Get("/{serverId}/vote-kick/threshold")
    override fun getVoteKickThreshold(auth: Authentication, @PathVariable serverId: Long): Int = service.getVoteKickThreshold(auth,serverId)

    @Put("/{serverId}/vote-kick/threshold")
    override fun setVoteKickThreshold(auth: Authentication, @PathVariable serverId: Long, @QueryValue() threshold: Int) = service.setVoteKickThreshold(auth,serverId, threshold)

    @Delete("/{serverId}/vote-kick/threshold")
    override fun resetVoteKickThreshold(auth: Authentication, @PathVariable serverId: Long) = service.resetVoteKickThreshold(auth,serverId)

}
