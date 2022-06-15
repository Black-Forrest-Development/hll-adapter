package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllMapAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/map")
@Tag(name = "Map API")
class HllMapController(private val service: RconService) : HllMapAPI {
    @Get("/available")
    override fun getMaps(auth: Authentication): Set<String> = service.getMaps(auth)

    @Get()
    override fun getCurrentMap(auth: Authentication): String = service.getCurrentMap(auth)

    @Post("/{name}")
    override fun setCurrentMap(auth: Authentication, @PathVariable name: String) = service.changeMap(auth, name)

    @Get("/rotation")
    override fun getMapsInRotation(auth: Authentication): Set<String> = service.getMapsInRotation(auth)

    @Post("/rotation/{name}")
    override fun addMapToRotation(auth: Authentication, @PathVariable name: String) = service.addMapToRotation(auth, name)

    @Delete("/rotation/{name}")
    override fun removeMapFromRotation(auth: Authentication, @PathVariable name: String) = service.removeMapFromRotation(auth, name)
}
