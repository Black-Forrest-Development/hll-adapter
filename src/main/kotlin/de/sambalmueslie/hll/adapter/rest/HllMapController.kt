package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllMapAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/map")
@Tag(name = "Map API")
class HllMapController(private val service: HllMapService) : HllMapAPI {
    @Get("/{serverId}/available")
    override fun getMaps(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getMaps(auth,serverId)

    @Get("/{serverId}")
    override fun getCurrentMap(auth: Authentication, @PathVariable serverId: Long): String = service.getCurrentMap(auth,serverId)

    @Post("/{serverId}/{name}")
    override fun setCurrentMap(auth: Authentication, @PathVariable serverId: Long, @PathVariable name: String) = service.setCurrentMap(auth,serverId, name)

    @Get("/{serverId}/rotation")
    override fun getMapsInRotation(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getMapsInRotation(auth,serverId)

    @Post("/{serverId}/rotation/{name}")
    override fun addMapToRotation(auth: Authentication, @PathVariable serverId: Long, @PathVariable name: String) = service.addMapToRotation(auth,serverId, name)

    @Delete("/{serverId}/rotation/{name}")
    override fun removeMapFromRotation(auth: Authentication, @PathVariable serverId: Long, @PathVariable name: String) = service.removeMapFromRotation(auth,serverId, name)
}
