package com.example.taboocards.ui.game_activity.score

import android.widget.Button

private const val point: Int = 1
private val skip: Int = 3

class ScoreCoordinator {

    fun addPoints(teamPoints: Int): Int {
        return teamPoints + point
    }

    fun subtractPoint(teamPoints: Int): Int {
        return teamPoints - point
    }

    fun skipCard(){

    }


}