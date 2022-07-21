package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllVipMgtAPI {
    fun getNumVipSlots(auth: Authentication, serverId: Long): Int
    fun setNumVipSlots(auth: Authentication, serverId: Long, max: Int) : Any

    fun getVipIds(auth: Authentication, serverId: Long): Set<String>
    fun vipAdd(auth: Authentication, serverId: Long, steamId: String, description: String = "") : Any
    fun vipRemove(auth: Authentication, serverId: Long, steamId: String) : Any
}
