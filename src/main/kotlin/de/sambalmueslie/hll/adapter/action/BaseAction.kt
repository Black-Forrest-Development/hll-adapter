package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.Action
import de.sambalmueslie.hll.adapter.rcon.RconClientService
import de.sambalmueslie.hll.adapter.rcon.api.HllRconRequest
import de.sambalmueslie.hll.adapter.rcon.api.HllRconResponse
import de.sambalmueslie.hll.adapter.rest.error.RequestFailedException
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger

abstract class BaseAction(
    private val clientService: RconClientService,
    private val logger: Logger
) : Action {
    fun check(auth: Authentication) {
        // TODO check for authentication
    }

    protected fun getInt(auth: Authentication, serverId: Long, command: String) = execute(auth, serverId, HllRconRequest(command)) { it.getInt() }
    protected fun getBoolean(auth: Authentication, serverId: Long, command: String) = execute(auth, serverId, HllRconRequest(command)) { it.getBoolean() }
    protected fun getString(auth: Authentication, serverId: Long, command: String) = execute(auth, serverId, HllRconRequest(command)) { it.getString() }
    protected fun getList(auth: Authentication, serverId: Long, command: String) = execute(auth, serverId, HllRconRequest(command)) { it.getList() }
    protected fun getSet(auth: Authentication, serverId: Long, command: String) = execute(auth, serverId, HllRconRequest(command)) { it.getSet() }
    protected fun setValue(auth: Authentication, serverId: Long, command: String): String = execute(auth, serverId, HllRconRequest(command)) { it.getString() }
    protected fun setValue(auth: Authentication, serverId: Long, request: HllRconRequest): String = execute(auth, serverId, request) { it.getString() }
    protected fun setValue(auth: Authentication, serverId: Long, builder: () -> HllRconRequest): String = execute(auth, serverId, builder.invoke()) { it.getString() }
    protected fun execute(auth: Authentication, serverId: Long, command: String): String = execute(auth, serverId, HllRconRequest(command)) { it.getString() }
    protected fun execute(auth: Authentication, serverId: Long, request: HllRconRequest): String = execute(auth, serverId, request) { it.getString() }
    protected fun execute(auth: Authentication, serverId: Long, builder: () -> HllRconRequest): String = execute(auth, serverId, builder.invoke()) { it.getString() }

    protected fun <T> execute(auth: Authentication, serverId: Long, request: HllRconRequest, converter: (response: HllRconResponse) -> T): T {
        check(auth)
        val client = clientService.getClient(auth, serverId) ?: throw RequestFailedException("Failed to execute ${request.content} cause no client was found for $serverId")
        val result = client.send(request)
        logger.debug("[${auth.name}] execute - ${request.content} => $result")
        return if (result.success) converter.invoke(result) else throw RequestFailedException("Failed to execute ${request.content} with ${result.content}")
    }


}
