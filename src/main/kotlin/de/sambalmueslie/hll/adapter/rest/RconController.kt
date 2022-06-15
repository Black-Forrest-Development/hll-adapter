package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.ConnectRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("api/rcon")
@Tag(name = "RconController")
class RconController(private val service: RconService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconController::class.java)
    }

    @Post("/connect")
    fun connect(auth: Authentication, @Body request: ConnectRequest) = service.connect(request)

}
