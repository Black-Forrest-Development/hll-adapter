package de.sambalmueslie.hll.adapter.action.api

data class ExecuteActionResponse(
    val action: String,
    val command: String,
    val success: Boolean
)
