package com.example.taboocards.ui.game_activity.timer

import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import com.example.taboocards.ui.game_activity.minuteInMilliseconds
import com.example.taboocards.ui.game_activity.secondInMilliseconds
import java.util.*

class TimerCoordinator {

    private lateinit var countDownTimer: CountDownTimer

    fun startTimer(timeInSeconds: Long, timeInMinutes: Long, textView: TextView) {
       var totalTime =
            (timeInSeconds * secondInMilliseconds) + (timeInMinutes * minuteInMilliseconds)

        countDownTimer = object : CountDownTimer(totalTime, 500) {
            override fun onTick(millisUntilFinished: Long) {
                totalTime = millisUntilFinished
                textView.text = updateCountDownText(totalTime)
            }

            override fun onFinish() {
                countDownTimer.cancel()
            }
        }.start()
    }

    private fun updateCountDownText(totalTime: Long): String {
        val minutes: Int = ((totalTime / 1000) / 60).toInt();
        val seconds: Int = ((totalTime / 1000) % 60).toInt();
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        Log.e("time", timeLeftFormatted)

        return timeLeftFormatted
    }

}
