package com.example.taboocards.ui.game_activity.score

import android.database.Observable

interface ScoreRepositoryImpl {

    fun addPoint(score: Score)

    fun getPoints(teamName: String): Observable<Score>

    fun updatePoint(score: Score)

    fun deletePoint(score: Score)

    fun deleteAllScores()

}