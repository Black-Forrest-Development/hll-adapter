package de.sambalmueslie.hll.adapter.server


import de.sambalmueslie.hll.adapter.permission.api.PermissionSchemeChangeRequest
import de.sambalmueslie.hll.adapter.server.api.ServerConfigChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/server/config")
@Tag(name = "Server Config API")
class ServerConfigController(private val service: ServerConfigService) {

    @Get()
    fun get(authentication: Authentication, pageable: Pageable) = service.get(authentication, pageable)

    @Get("/{id}")
    fun get(authentication: Authentication, @PathVariable id: Long) = service.get(authentication, id)

    @Post()
    fun create(authentication: Authentication, @Body request: ServerConfigChangeRequest) = service.create(authentication, request)

    @Put("/{id}")
    fun update(authentication: Authentication, @PathVariable id: Long, @Body request: ServerConfigChangeRequest) = service.update(authentication, id, request)

    @Delete("/{id}")
    fun delete(authentication: Authentication, @PathVariable id: Long) = service.delete(authentication, id)

}
