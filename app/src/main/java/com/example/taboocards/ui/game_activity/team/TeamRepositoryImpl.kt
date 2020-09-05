package com.example.taboocards.ui.game_activity.team

class TeamRepositoryImpl(
    private val teamDao: TeamDao
) : TeamRepository {


    override fun addTeam(team: Team) {
        teamDao.addTeam(team)
    }

    override fun getTeam(teamName: String?): Team {
        return teamDao.getTeam(teamName)
    }

    override fun updateTeam(team: Team) {
        teamDao.updateTeam(team)
    }

    override fun deleteAllTeams() {
        teamDao.deleteAllTeams()
    }

    override fun getAllTeams(): List<Team> {
        TODO("Not yet implemented")
    }

    override fun deleteTeam(team: Team) {
        TODO("Not yet implemented")
    }

}