package de.sambalmueslie.hll.adapter.permission


import de.sambalmueslie.hll.adapter.permission.api.PermissionSchemeChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/permission/scheme")
@Tag(name = "Permission Scheme API")
class PermissionSchemeController(private val service: PermissionSchemeService) {

    @Get()
    fun get(authentication: Authentication, pageable: Pageable) = service.get(authentication, pageable)

    @Get("/{id}")
    fun get(authentication: Authentication, @PathVariable id: Long) = service.get(authentication, id)

    @Post()
    fun create(authentication: Authentication, @Body request: PermissionSchemeChangeRequest) = service.create(authentication, request)

    @Put("/{id}")
    fun update(authentication: Authentication, @PathVariable id: Long, @Body request: PermissionSchemeChangeRequest) = service.update(authentication, id, request)

    @Delete("/{id}")
    fun delete(authentication: Authentication, @PathVariable id: Long) = service.delete(authentication, id)
}
