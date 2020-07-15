package com.example.taboocards.ui.game_activity.timer

import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import java.util.*

const val minuteInMilliseconds: Long = 60000L
const val secondInMilliseconds: Long = 1000L

class TimerCoordinator() {

    fun startTimer(totalTime: Long, textView: TextView) {
        var countDownTimer: CountDownTimer? = null

        countDownTimer = object : CountDownTimer(totalTime, 500) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = updateCountDownText(millisUntilFinished)
            }

            override fun onFinish() {
                countDownTimer?.cancel()
            }

        }.start()
    }

    private fun updateCountDownText(totalTime: Long): String {
        val minutes: Int = ((totalTime / secondInMilliseconds) / 60).toInt();
        val seconds: Int = ((totalTime / secondInMilliseconds) % 60).toInt();
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        Log.e("time", timeLeftFormatted)

        return timeLeftFormatted
    }

}
