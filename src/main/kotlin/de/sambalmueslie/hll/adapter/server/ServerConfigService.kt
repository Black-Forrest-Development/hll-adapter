package de.sambalmueslie.hll.adapter.server


import com.nimbusds.jose.shaded.json.JSONArray
import de.sambalmueslie.hll.adapter.permission.error.InvalidRequestException
import de.sambalmueslie.hll.adapter.permission.error.NotAuthorizedException
import de.sambalmueslie.hll.adapter.server.api.ServerConfig
import de.sambalmueslie.hll.adapter.server.api.ServerConfigChangeRequest
import de.sambalmueslie.hll.adapter.server.db.ServerConfigData
import de.sambalmueslie.hll.adapter.server.db.ServerConfigRepository
import de.sambalmueslie.hll.adapter.util.findByIdOrNull
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class ServerConfigService(private val repository: ServerConfigRepository) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ServerConfigService::class.java)
        private const val READ_ROLE = "server.config.read"
        private const val WRITE_ROLE = "server.config.write"
    }


    fun get(auth: Authentication, pageable: Pageable): Page<ServerConfig> {
        isReadAuthorized(auth)
        return repository.findAll(pageable).map { it.convert() }
    }

    fun get(auth: Authentication, id: Long): ServerConfig? {
        isReadAuthorized(auth)
        return repository.findByIdOrNull(id)?.convert()
    }

    private fun isReadAuthorized(auth: Authentication) {
        val permissions = auth.attributes["permissions"] as JSONArray
        if (permissions.contains(READ_ROLE)) return
        throw NotAuthorizedException(auth, "Server Config API", READ_ROLE, "User has not the required role")
    }


    fun create(auth: Authentication, request: ServerConfigChangeRequest): ServerConfig? {
        isWriteAuthorized(auth)
        logger.debug("[${auth.name}] CREATE SERVER CONFIG - $request")
        return create(request)
    }

    private fun create(request: ServerConfigChangeRequest): ServerConfig? {
        isRequestValid(request)

        val existing = repository.findByName(request.name)
        return if (existing != null) {
            update(existing, request)
        } else {
            return repository.save(ServerConfigData.create(request)).convert()
        }
    }

    fun update(auth: Authentication, id: Long, request: ServerConfigChangeRequest): ServerConfig? {
        isWriteAuthorized(auth)
        logger.debug("[${auth.name}] UPDATE SERVER CONFIG ($id) - $request")
        val data = repository.findByIdOrNull(id) ?: return create(request)
        return update(data, request)
    }

    private fun update(data: ServerConfigData, request: ServerConfigChangeRequest): ServerConfig? {
        isRequestValid(request)
        return repository.update(data.update(request)).convert()
    }

    fun delete(auth: Authentication, id: Long) {
        isWriteAuthorized(auth)
        logger.debug("[${auth.name}] DELETE SERVER CONFIG ($id)")
        repository.deleteById(id)
    }

    private fun isWriteAuthorized(auth: Authentication) {
        val permissions = auth.attributes["permissions"] as JSONArray
        if (permissions.contains(WRITE_ROLE)) return
        throw NotAuthorizedException(auth, "Server Config API", WRITE_ROLE, "User has not the required role")
    }

    private fun isRequestValid(request: ServerConfigChangeRequest) {
        if (request.name.isBlank()) throw InvalidRequestException("Name must not blank")
    }
}
