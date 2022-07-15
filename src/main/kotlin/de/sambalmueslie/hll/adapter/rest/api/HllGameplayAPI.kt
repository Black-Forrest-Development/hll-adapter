package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllGameplayAPI {
    fun getTeamSwitchCoolDown(auth: Authentication, serverId: Long): Int
    fun setTeamSwitchCoolDown(auth: Authentication, serverId: Long, coolDown: Int) : Any

    fun isAutoBalanceEnabled(auth: Authentication, serverId: Long): Boolean
    fun setAutoBalanceEnabled(auth: Authentication, serverId: Long, enabled: Boolean) : Any

    fun getAutoBalanceThreshold(auth: Authentication, serverId: Long): Int
    fun setAutoBalanceThreshold(auth: Authentication, serverId: Long, threshold: Int) : Any

    fun getKickIdleTime(auth: Authentication, serverId: Long): Int
    fun setKickIdleTime(auth: Authentication, serverId: Long, minutes: Int) : Any

    fun getHighPingThreshold(auth: Authentication, serverId: Long): Int
    fun setHighPingThreshold(auth: Authentication, serverId: Long, millis: Int) : Any

    fun getMaxQueuedPlayers(auth: Authentication, serverId: Long): Int
    fun setMaxQueuedPlayers(auth: Authentication, serverId: Long, max: Int) : Any

    fun isVoteKickEnabled(auth: Authentication, serverId: Long): Boolean
    fun setVoteKickEnabled(auth: Authentication, serverId: Long, enabled: Boolean) : Any

    fun getVoteKickThreshold(auth: Authentication, serverId: Long): Int
    fun setVoteKickThreshold(auth: Authentication, serverId: Long, threshold: Int) : Any

    fun resetVoteKickThreshold(auth: Authentication, serverId: Long) : Any
}
