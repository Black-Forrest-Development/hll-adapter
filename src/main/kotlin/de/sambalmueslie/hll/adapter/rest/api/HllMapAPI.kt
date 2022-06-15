package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllMapAPI {

    fun getMaps(auth: Authentication): Set<String>

    fun getCurrentMap(auth: Authentication): String
    fun setCurrentMap(auth: Authentication, name: String): Any

    fun getMapsInRotation(auth: Authentication): Set<String>
    fun addMapToRotation(auth: Authentication, name: String): Any
    fun removeMapFromRotation(auth: Authentication, name: String): Any
}
