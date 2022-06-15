package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllServerAPI
import de.sambalmueslie.hll.adapter.rest.api.Message
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
@Controller("api/server")
@Tag(name = "Server API")
class HllServerController(private val service: RconService) : HllServerAPI {
    @Get("/name")
    override fun getName(auth: Authentication): String = service.getServerName(auth)
    @Get("/slots")
    override fun getSlots(auth: Authentication): Slots = service.getServerSlots(auth)
    @Put("/message")
    override fun setMessage(auth: Authentication, @Body message: Message) = service.setServerMessage(auth, message)
    @Post("/broadcast")
    override fun broadcast(auth: Authentication, @Body message: Message) = service.broadcast(auth, message)
    @Post("/log")
    override fun getAdminLog(auth: Authentication, @QueryValue(defaultValue = "10") minutes: Int, @QueryValue(defaultValue = "") filter: String): List<String> =
        service.getAdminLog(auth, minutes, filter)
}
