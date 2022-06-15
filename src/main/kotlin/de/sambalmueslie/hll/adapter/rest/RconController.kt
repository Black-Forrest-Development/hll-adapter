package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.ConnectRequest
import de.sambalmueslie.hll.adapter.rest.api.HllRconAPI
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("api/rcon")
@Tag(name = "RconController")
class RconController(private val service: RconService) : HllRconAPI {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconController::class.java)
    }

    @Post("/connect")
    fun connect(auth: Authentication, @Body request: ConnectRequest) = service.connect(request)

}
