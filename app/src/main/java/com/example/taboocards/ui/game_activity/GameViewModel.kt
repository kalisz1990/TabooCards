package com.example.taboocards.ui.game_activity

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator

class GameViewModel(private var timerCoordinator: TimerCoordinator) : ViewModel() {


//    fun getCards() = cardRepository.getCards()
//    //TODO: get only 1 random carc


    fun startTimer(totalTime: Long, textView: TextView) {
        timerCoordinator.startTimer(totalTime, textView)
    }

}
