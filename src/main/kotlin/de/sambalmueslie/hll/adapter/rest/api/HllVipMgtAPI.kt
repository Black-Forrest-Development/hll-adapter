package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllVipMgtAPI {
    fun getNumVipSlots(auth: Authentication): Int
    fun setNumVipSlots(auth: Authentication, max: Int) : Any

    fun getVipIds(auth: Authentication): Set<String>
    fun vipAdd(auth: Authentication, steamId: String, description: String = "") : Any
    fun vipRemove(auth: Authentication, steamId: String) : Any
}
