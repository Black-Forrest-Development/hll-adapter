package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllServerAPI  {
    fun getName(auth: Authentication): String
    fun getSlots(auth: Authentication): Slots
    fun setMessage(auth: Authentication, message: Message): Any
    fun broadcast(auth: Authentication, message: Message): Any
    fun getAdminLog(auth: Authentication, minutes: Int, filter: String = ""): List<String>
}
