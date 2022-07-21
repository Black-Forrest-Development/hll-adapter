package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllPlayerAPI {
    fun getTempBans(auth: Authentication, serverId: Long): Set<String>
    fun playerBanTempByName(auth: Authentication, serverId: Long, name: String, hours: Int, reason: String, admin: String): Any
    fun playerBanTempBySteamId(auth: Authentication, serverId: Long, steamId: String, hours: Int, reason: String, admin: String): Any
    fun playerBanTempRemove(auth: Authentication, serverId: Long, player: String): Any

    fun getPermanentBans(auth: Authentication, serverId: Long): Set<String>
    fun playerBanPermanentByName(auth: Authentication, serverId: Long, name: String, reason: String, admin: String): Any
    fun playerBanPermanentBySteamId(auth: Authentication, serverId: Long, steamId: String, reason: String, admin: String): Any
    fun playerBanPermanentRemove(auth: Authentication, serverId: Long, player: String): Any

    fun playerPunish(auth: Authentication, serverId: Long, player: String, reason: String): Any
    fun playerKick(auth: Authentication, serverId: Long, player: String, reason: String): Any

    fun getPlayerNames(auth: Authentication, serverId: Long): Set<String>

    fun getPlayerInfo(auth: Authentication, serverId: Long, player: String): Any

    fun switchPlayerOnDeath(auth: Authentication, serverId: Long, player: String): Any
    fun switchPlayerImmediately(auth: Authentication, serverId: Long, player: String): Any
}
