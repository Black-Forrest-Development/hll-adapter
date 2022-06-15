package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllVipMgtAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/vip")
@Tag(name = "VIP Mgt API")
class HllVipMgtController(private val service: RconService) : HllVipMgtAPI {
    @Get("/slots")
    override fun getNumVipSlots(auth: Authentication): Int = service.getNumVipSlots(auth)

    @Put("/slots")
    override fun setNumVipSlots(auth: Authentication, @QueryValue() max: Int) = service.setNumVipSlots(auth, max)

    @Get()
    override fun getVipIds(auth: Authentication): Set<String> = service.getVipIds(auth)

    @Post("/{steamId}")
    override fun vipAdd(auth: Authentication, @PathVariable steamId: String, @QueryValue() description: String) = service.vipAdd(auth, steamId, description)

    @Delete("/{steamId}")
    override fun vipRemove(auth: Authentication, @PathVariable steamId: String) = service.vipRemove(auth, steamId)


}
