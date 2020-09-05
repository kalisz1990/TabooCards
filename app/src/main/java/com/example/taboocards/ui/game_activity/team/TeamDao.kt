package com.example.taboocards.ui.game_activity.team

import androidx.room.*

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeam(team: Team)

    @Query("SELECT * FROM table_team WHERE team_name = :teamName  ")
    fun getTeam(teamName: String?): Team

    @Query("SELECT * FROM table_team")
    fun getAllTeams(): List<Team>

    @Update
    fun updateTeam(team: Team)

    @Delete
    fun deleteTeam(team: Team)

    @Query("DELETE FROM table_team")
    fun deleteAllTeams()

}