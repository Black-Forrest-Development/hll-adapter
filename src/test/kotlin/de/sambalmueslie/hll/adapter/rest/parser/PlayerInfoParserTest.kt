package de.sambalmueslie.hll.adapter.rest.parser

import de.sambalmueslie.hll.adapter.rest.api.PlayerInfo
import de.sambalmueslie.hll.adapter.rest.api.PlayerScore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class PlayerInfoParserTest {

    private val parser = PlayerInfoParser()

    @Test
    fun `parse unassigned player info`() {
        val name = "Sample Name"
        val steamId = "12345678912345678"
        val team = "Axis"
        val role = "Rifleman"
        val loadout = "Standard Issue"
        val kills = 0
        val deaths = 0

        val combat: Int = 999
        val offensive: Int = 0
        val defensive: Int = 123
        val support: Int = 567
        val level = 123
        val content = """
            Name: $name
            steamID64: $steamId
            Team: $team
            Role: $role
            Loadout: $loadout
            Kills: $kills - Deaths: $deaths
            Score: C $combat, O $offensive, D $defensive, S $support
            Level: $level
        """.trimIndent()

        val result = parser.parsePlayerInfo(content) as? PlayerInfo
        assertNotNull(result)

        assertEquals(PlayerInfo(name, steamId, team, role, "Unknown", loadout, kills, deaths, PlayerScore(combat, offensive, defensive, support), level), result)

    }

    @Test
    fun `parse assigned player info`() {
        val name = "Sample Name"
        val steamId = "12345678912345678"
        val team = "Axis"
        val role = "Rifleman"
        val unit = "0 - ABLE"
        val loadout = "Standard Issue"
        val kills = 0
        val deaths = 0

        val combat: Int = 999
        val offensive: Int = 0
        val defensive: Int = 123
        val support: Int = 567
        val level = 123
        val content = """
            Name: $name
            steamID64: $steamId
            Team: $team
            Role: $role
            Unit: $unit
            Loadout: $loadout
            Kills: $kills - Deaths: $deaths
            Score: C $combat, O $offensive, D $defensive, S $support
            Level: $level
        """.trimIndent()

        val result = parser.parsePlayerInfo(content) as? PlayerInfo
        assertNotNull(result)

        assertEquals(PlayerInfo(name, steamId, team, role, unit, loadout, kills, deaths, PlayerScore(combat, offensive, defensive, support), level), result)
    }
}
