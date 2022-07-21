package de.sambalmueslie.hll.adapter.permission


import com.nimbusds.jose.shaded.json.JSONArray
import de.sambalmueslie.hll.adapter.permission.api.PermissionScheme
import de.sambalmueslie.hll.adapter.permission.api.PermissionSchemeChangeRequest
import de.sambalmueslie.hll.adapter.permission.db.PermissionSchemeData
import de.sambalmueslie.hll.adapter.permission.db.PermissionSchemeRepository
import de.sambalmueslie.hll.adapter.permission.error.InvalidRequestException
import de.sambalmueslie.hll.adapter.permission.error.NotAuthorizedException
import de.sambalmueslie.hll.adapter.util.findByIdOrNull
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class PermissionSchemeService(private val repository: PermissionSchemeRepository) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PermissionSchemeService::class.java)
        private const val READ_ROLE = "permission.scheme.read"
        private const val WRITE_ROLE = "permission.scheme.write"
    }

    fun get(auth: Authentication, pageable: Pageable): Page<PermissionScheme> {
        isReadAuthorized(auth)
        return repository.findAll(pageable).map { it.convert() }
    }

    fun get(auth: Authentication, id: Long): PermissionScheme? {
        isReadAuthorized(auth)
        return repository.findByIdOrNull(id)?.convert()
    }

    private fun isReadAuthorized(auth: Authentication) {
        val permissions = auth.attributes["permissions"] as JSONArray
        if (permissions.contains(READ_ROLE)) return
        throw NotAuthorizedException(auth, "Permission Scheme API", READ_ROLE, "User has not the required role")
    }


    fun create(auth: Authentication, request: PermissionSchemeChangeRequest): PermissionScheme? {
        isWriteAuthorized(auth)
        logger.debug("[${auth.name}] CREATE PERMISSION SCHEME - $request")
        return create(request)
    }

    private fun create(request: PermissionSchemeChangeRequest): PermissionScheme? {
        isRequestValid(request)

        val existing = repository.findByName(request.name)
        return if (existing != null) {
            update(existing, request)
        } else {
            return repository.save(PermissionSchemeData.create(request)).convert()
        }
    }

    fun update(auth: Authentication, id: Long, request: PermissionSchemeChangeRequest): PermissionScheme? {
        isWriteAuthorized(auth)
        logger.debug("[${auth.name}] UPDATE PERMISSION SCHEME ($id) - $request")
        val data = repository.findByIdOrNull(id) ?: return create(request)
        return update(data, request)
    }

    private fun update(data: PermissionSchemeData, request: PermissionSchemeChangeRequest): PermissionScheme? {
        isRequestValid(request)
        return repository.update(data.update(request)).convert()
    }

    fun delete(auth: Authentication, id: Long) {
        isWriteAuthorized(auth)
        logger.debug("[${auth.name}] DELETE PERMISSION SCHEME ($id)")
        repository.deleteById(id)
    }

    private fun isWriteAuthorized(auth: Authentication) {
        val permissions = auth.attributes["permissions"] as JSONArray
        if (permissions.contains(WRITE_ROLE)) return
        throw NotAuthorizedException(auth, "Permission Scheme API", WRITE_ROLE, "User has not the required role")
    }

    private fun isRequestValid(request: PermissionSchemeChangeRequest) {
        if (request.name.isBlank()) throw InvalidRequestException("Name must not blank")
    }
}
