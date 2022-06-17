package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.action.ActionService
import de.sambalmueslie.hll.adapter.rest.api.ConnectRequest
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class RconService(private val actionService: ActionService) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RconService::class.java)
    }

    fun connect(request: ConnectRequest) = actionService.connect(request)

}
