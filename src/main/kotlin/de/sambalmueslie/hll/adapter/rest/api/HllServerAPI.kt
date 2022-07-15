package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllServerAPI {
    fun getName(auth: Authentication, serverId: Long): String
    fun getSlots(auth: Authentication, serverId: Long): Slots
    fun setMessage(auth: Authentication, serverId: Long, message: Message): Any
    fun broadcast(auth: Authentication, serverId: Long, message: Message): Any
    fun getAdminLog(auth: Authentication, serverId: Long, minutes: Int, filter: String = ""): List<String>
}
