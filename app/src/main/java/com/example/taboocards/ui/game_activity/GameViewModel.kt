package com.example.taboocards.ui.game_activity

import android.os.CountDownTimer
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.taboocards.data.card.CardRepository
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class GameViewModel(private var timerCoordinator: TimerCoordinator) : ViewModel() {


//    fun getCards() = cardRepository.getCards()
//    //TODO: get only 1 random carc


    fun startTimer(totalTime: Long, textView: TextView) {
        timerCoordinator.startTimer(totalTime, textView)
    }

}
