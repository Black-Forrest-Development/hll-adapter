package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.Action
import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.api.HllRconRequest
import de.sambalmueslie.hll.adapter.rcon.api.HllRconResponse
import de.sambalmueslie.hll.adapter.rest.error.RequestFailedException
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger

abstract class BaseAction(
    private val logger: Logger
) : Action {
    fun check(auth: Authentication) {
        // TODO check for authentication
    }

    protected fun <T> execute(auth: Authentication, command: String, function: (cmd: String) -> T): T {
        check(auth)
        val result = function.invoke(command)
        logger.debug("[${auth.name}] execute - $command => $result")
        return result
    }

    protected fun getInt(auth: Authentication, client: HllRconClient, command: String) = execute(auth, client, HllRconRequest(command)) { it.getInt() }
    protected fun getBoolean(auth: Authentication, client: HllRconClient, command: String) = execute(auth, client, HllRconRequest(command)) { it.getBoolean() }
    protected fun getString(auth: Authentication, client: HllRconClient, command: String) = execute(auth, client, HllRconRequest(command)) { it.getString() }
    protected fun getList(auth: Authentication, client: HllRconClient, command: String) = execute(auth, client, HllRconRequest(command)) { it.getList() }
    protected fun getSet(auth: Authentication, client: HllRconClient, command: String) = execute(auth, client, HllRconRequest(command)) { it.getSet() }
    protected fun setValue(auth: Authentication, client: HllRconClient, command: String): String = execute(auth, client, HllRconRequest(command)) { it.getString() }
    protected fun setValue(auth: Authentication, client: HllRconClient, request: HllRconRequest): String = execute(auth, client, request) { it.getString() }
    protected fun setValue(auth: Authentication, client: HllRconClient, builder: () -> HllRconRequest): String = execute(auth, client, builder.invoke()) { it.getString() }

    protected fun execute(auth: Authentication, client: HllRconClient, command: String): String = execute(auth, client, HllRconRequest(command)) { it.getString() }
    protected fun execute(auth: Authentication, client: HllRconClient, request: HllRconRequest): String = execute(auth, client, request) { it.getString() }
    protected fun execute(auth: Authentication, client: HllRconClient, builder: () -> HllRconRequest): String = execute(auth, client, builder.invoke()) { it.getString() }

    protected fun <T> execute(auth: Authentication, client: HllRconClient, request: HllRconRequest, converter: (response: HllRconResponse) -> T): T {
        check(auth)
        val result = client.send(request)
        logger.debug("[${auth.name}] execute - ${request.content} => $result")
        return if (result.success) converter.invoke(result) else throw RequestFailedException("Failed to execute ${request.content} with ${result.content}")
    }


}
