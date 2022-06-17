package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllAdminMgtAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/admin")
@Tag(name = "Admin Mgt API")
class HllAdminMgtController(private val service: HllAdminMgtService) : HllAdminMgtAPI {

    @Get("/groups")
    override fun getAdminGroups(auth: Authentication): Set<String> = service.getAdminGroups(auth)

    @Get()
    override fun getAdminIds(auth: Authentication): Set<String> = service.getAdminIds(auth)

    @Post("/{steamId}")
    override fun adminAdd(auth: Authentication, @PathVariable steamId: String, @QueryValue() group: String, @QueryValue() comment: String) = service.adminAdd(auth, steamId, group, comment)

    @Delete("/{steamId}")
    override fun adminRemove(auth: Authentication, @PathVariable steamId: String) = service.adminRemove(auth, steamId)
}
