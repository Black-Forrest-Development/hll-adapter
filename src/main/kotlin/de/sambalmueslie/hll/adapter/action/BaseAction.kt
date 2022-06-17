package de.sambalmueslie.hll.adapter.action


import de.sambalmueslie.hll.adapter.action.api.Action
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger

abstract class BaseAction(private val logger: Logger) : Action {
    fun check(auth: Authentication) {
        // TODO check for authentication
    }

    protected fun <T> execute(auth: Authentication, command: String, function: (cmd: String) -> T): T {
        check(auth)
        val result = function.invoke(command)
        logger.debug("[${auth.name}] execute - $command => $result")
        return result
    }
}
