package com.example.taboocards.ui.game_activity.score

import android.database.Observable
import androidx.room.*

@Dao
interface ScoreDao {

    @Insert
    fun addPoint(score: Score)

    @Query("SELECT * FROM table_score WHERE team_name = :teamName  ")
    fun getPoints(teamName: String): Observable<Score>

    @Update
    fun updatePoint(score: Score)

    @Delete
    fun deletePoint(score: Score)

    @Query("DELETE FROM table_score")
    fun deleteAllScores()

}