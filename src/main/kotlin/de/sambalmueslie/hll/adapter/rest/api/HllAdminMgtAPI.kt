package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllAdminMgtAPI {
    fun getAdminGroups(auth: Authentication, serverId: Long): Set<String>
    fun getAdminIds(auth: Authentication, serverId: Long): Set<String>
    fun adminAdd(auth: Authentication, serverId: Long, steamId: String, group: String, comment: String = ""): Any
    fun adminRemove(auth: Authentication, serverId: Long, steamId: String): Any
}
