package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllAdminMgtAPI {
    fun getAdminGroups(auth: Authentication): Set<String>

    fun getAdminIds(auth: Authentication): Set<String>
    fun adminAdd(auth: Authentication, steamId: String, group: String, comment: String = "") : Any
    fun adminRemove(auth: Authentication, steamId: String) : Any
}
