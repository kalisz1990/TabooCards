package com.example.taboocards.ui.game_activity.teams

interface TeamRepository {

    fun addTeam(team: Team)

    fun getTeam(teamName: String?): Team

    fun getAllTeams(): List<Team>

    fun updateTeam(team: Team)

    fun deleteTeam(team: Team)

    fun deleteAllTeams()
}

