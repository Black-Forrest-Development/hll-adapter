package de.sambalmueslie.hll.adapter.rest.action


import io.micronaut.security.authentication.Authentication

abstract class BaseAction : Action {
    fun check(auth: Authentication) {
        // TODO check for authentication
    }
}
