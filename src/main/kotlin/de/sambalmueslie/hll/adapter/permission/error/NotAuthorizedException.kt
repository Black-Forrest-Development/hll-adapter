package de.sambalmueslie.hll.adapter.permission.error


import io.micronaut.security.authentication.Authentication

class NotAuthorizedException(
    val auth: Authentication,
    val api: String,
    val role: String,
    override val message: String
) : RuntimeException(message) {
    fun error() = NotAuthorizedError(auth, api, role, message)
}
