package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllGameplayAPI {
    fun getTeamSwitchCoolDown(auth: Authentication): Int
    fun setTeamSwitchCoolDown(auth: Authentication, coolDown: Int) : Any

    fun isAutoBalanceEnabled(auth: Authentication): Boolean
    fun setAutoBalanceEnabled(auth: Authentication, enabled: Boolean) : Any

    fun getAutoBalanceThreshold(auth: Authentication): Int
    fun setAutoBalanceThreshold(auth: Authentication, threshold: Int) : Any

    fun getKickIdleTime(auth: Authentication): Int
    fun setKickIdleTime(auth: Authentication, minutes: Int) : Any

    fun getHighPingThreshold(auth: Authentication): Int
    fun setHighPingThreshold(auth: Authentication, millis: Int) : Any

    fun getMaxQueuedPlayers(auth: Authentication): Int
    fun setMaxQueuedPlayers(auth: Authentication, max: Int) : Any

    fun isVoteKickEnabled(auth: Authentication): Boolean
    fun setVoteKickEnabled(auth: Authentication, enabled: Boolean) : Any

    fun getVoteKickThreshold(auth: Authentication): Int
    fun setVoteKickThreshold(auth: Authentication, threshold: Int) : Any

    fun resetVoteKickThreshold(auth: Authentication) : Any
}
