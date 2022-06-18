package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllPlayerAPI {
    fun getTempBans(auth: Authentication): Set<String>
    fun playerBanTempByName(auth: Authentication, name: String, hours: Int, reason: String, admin: String): Any
    fun playerBanTempBySteamId(auth: Authentication, steamId: String, hours: Int, reason: String, admin: String): Any
    fun playerBanTempRemove(auth: Authentication, player: String): Any

    fun getPermanentBans(auth: Authentication): Set<String>
    fun playerBanPermanentByName(auth: Authentication, name: String, reason: String, admin: String): Any
    fun playerBanPermanentBySteamId(auth: Authentication, steamId: String, reason: String, admin: String): Any
    fun playerBanPermanentRemove(auth: Authentication, player: String): Any

    fun playerPunish(auth: Authentication, player: String, reason: String): Any
    fun playerKick(auth: Authentication, player: String, reason: String): Any

    fun getPlayerNames(auth: Authentication): Set<String>

    fun getPlayerInfo(auth: Authentication, player: String): Any

    fun switchPlayerOnDeath(auth: Authentication, player: String): Any
    fun switchPlayerImmediately(auth: Authentication, player: String): Any
}
