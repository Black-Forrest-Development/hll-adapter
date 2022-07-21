package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllMapAPI {

    fun getMaps(auth: Authentication, serverId: Long): Set<String>

    fun getCurrentMap(auth: Authentication, serverId: Long): String
    fun setCurrentMap(auth: Authentication, serverId: Long, name: String): Any

    fun getMapsInRotation(auth: Authentication, serverId: Long): Set<String>
    fun addMapToRotation(auth: Authentication, serverId: Long, name: String): Any
    fun removeMapFromRotation(auth: Authentication, serverId: Long, name: String): Any
}
