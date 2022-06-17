package de.sambalmueslie.hll.adapter.permission


import io.micronaut.http.annotation.Controller
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("api/permission")
@Tag(name = "Admin Mgt API")
class PermissionController(private val service: PermissionService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(PermissionController::class.java)
    }


}
