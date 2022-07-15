package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllAdminMgtAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/admin")
@Tag(name = "Admin Mgt API")
class HllAdminMgtController(private val service: HllAdminMgtService) : HllAdminMgtAPI {

    @Get("/{serverId}/groups")
    override fun getAdminGroups(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getAdminGroups(auth, serverId)

    @Get("/{serverId}")
    override fun getAdminIds(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getAdminIds(auth, serverId)

    @Post("/{serverId}/{steamId}")
    override fun adminAdd(auth: Authentication, @PathVariable serverId: Long, @PathVariable steamId: String, @QueryValue() group: String, @QueryValue() comment: String) =
        service.adminAdd(auth, serverId, steamId, group, comment)

    @Delete("/{serverId}/{steamId}")
    override fun adminRemove(auth: Authentication, @PathVariable serverId: Long, @PathVariable steamId: String) = service.adminRemove(auth, serverId, steamId)
}
