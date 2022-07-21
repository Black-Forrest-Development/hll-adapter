package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllVipMgtAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/vip")
@Tag(name = "VIP Mgt API")
class HllVipMgtController(private val service: HllVipMgtService) : HllVipMgtAPI {
    @Get("/{serverId}/slots")
    override fun getNumVipSlots(auth: Authentication, @PathVariable serverId: Long): Int = service.getNumVipSlots(auth,serverId)

    @Put("/{serverId}/slots")
    override fun setNumVipSlots(auth: Authentication, @PathVariable serverId: Long, @QueryValue() max: Int) = service.setNumVipSlots(auth,serverId, max)

    @Get("/{serverId}")
    override fun getVipIds(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getVipIds(auth,serverId)

    @Post("/{serverId}/{steamId}")
    override fun vipAdd(auth: Authentication, @PathVariable serverId: Long, @PathVariable steamId: String, @QueryValue() description: String) = service.vipAdd(auth,serverId, steamId, description)

    @Delete("/{serverId}/{steamId}")
    override fun vipRemove(auth: Authentication, @PathVariable serverId: Long, @PathVariable steamId: String) = service.vipRemove(auth,serverId, steamId)


}
