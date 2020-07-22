package com.example.taboocards.ui.game_activity.score

import android.database.Observable

class ScoreRepository(private val scoreDao: ScoreDao) : ScoreRepositoryImpl {

    override fun addPoint(score: Score) = scoreDao.addPoint(score)

    override fun getPoints(teamName: String): Observable<Score> = scoreDao.getPoints(teamName)

    override fun updatePoint(score: Score) = scoreDao.updatePoint(score)

    override fun deletePoint(score: Score) = scoreDao.deletePoint(score)

    override fun deleteAllScores() = scoreDao.deleteAllScores()

}