package com.example.taboocards.ui.game_activity.timer

import android.os.CountDownTimer

class TimerCoordinator {

    private lateinit var countDownTimer: CountDownTimer
    private var timerLengthSeconds: Long = 120L
    private var secondsRemaining: Long = 0L

    fun startTimer(){
        timerPreSetup()
        countDownTimer.start()
    }

    private fun onTimerFinished() {
        secondsRemaining = timerLengthSeconds
    }

    fun timerPreSetup() {
        countDownTimer = object : CountDownTimer(timerLengthSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
            }
            override fun onFinish() = onTimerFinished()
        }
    }

}
