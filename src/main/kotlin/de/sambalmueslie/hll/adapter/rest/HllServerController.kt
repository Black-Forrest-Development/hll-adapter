package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllServerAPI
import de.sambalmueslie.hll.adapter.rest.api.Message
import de.sambalmueslie.hll.adapter.rest.api.Slots
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/server")
@Tag(name = "Server API")
class HllServerController(private val service: HllServerService) : HllServerAPI {
    @Get("/{serverId}/name")
    override fun getName(auth: Authentication, @PathVariable serverId: Long): String = service.getServerName(auth,serverId)
    @Get("/{serverId}/slots")
    override fun getSlots(auth: Authentication, @PathVariable serverId: Long): Slots = service.getServerSlots(auth,serverId)
    @Put("/{serverId}/message")
    override fun setMessage(auth: Authentication, @PathVariable serverId: Long, @Body message: Message) = service.setServerMessage(auth,serverId, message)
    @Post("/{serverId}/broadcast")
    override fun broadcast(auth: Authentication, @PathVariable serverId: Long, @Body message: Message) = service.broadcast(auth,serverId, message)
    @Post("/{serverId}/log")
    override fun getAdminLog(auth: Authentication, @PathVariable serverId: Long, @QueryValue(defaultValue = "10") minutes: Int, @QueryValue(defaultValue = "") filter: String): List<String> =
        service.getAdminLog(auth,serverId, minutes, filter)
}
