package de.sambalmueslie.hll.adapter.rest.api

import de.sambalmueslie.hll.adapter.action.api.ExecuteActionResponse
import de.sambalmueslie.hll.adapter.action.api.GetActionResponse
import io.micronaut.security.authentication.Authentication

interface HllServerAPI {
    fun getName(auth: Authentication, serverId: Long): GetActionResponse<String>
    fun getSlots(auth: Authentication, serverId: Long): Slots
    fun setMessage(auth: Authentication, serverId: Long, message: Message): Any
    fun broadcast(auth: Authentication, serverId: Long, message: Message): ExecuteActionResponse
    fun getAdminLog(auth: Authentication, serverId: Long, minutes: Int, filter: String = ""): List<String>
}
