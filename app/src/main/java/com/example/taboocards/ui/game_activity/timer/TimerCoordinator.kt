package com.example.taboocards.ui.game_activity.timer

import android.content.Context
import android.os.CountDownTimer
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.taboocards.R
import com.example.taboocards.ui.game_activity.dialog.DialogCreator
import java.util.*

const val minuteInMilliseconds: Long = 60000L
const val secondInMilliseconds: Long = 1000L

class TimerCoordinator(private val context: Context) {

    var countDownTimer: CountDownTimer? = null

    fun startTimer(totalTime: Long, textView: TextView, fm: FragmentManager) {

        countDownTimer = object : CountDownTimer(totalTime, 500) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = updateCountDownText(millisUntilFinished)
            }

            override fun onFinish() {
                countDownTimer?.cancel()

                DialogCreator().createDialog(
                    R.layout.start_dialog_game_activity,
                    context.getString(R.string.pass_phone),
                    fm
                )
            }

        }.start()
    }


    private fun updateCountDownText(totalTime: Long): String {
        val minutes: Int = ((totalTime / secondInMilliseconds) / 60).toInt()
        val seconds: Int = ((totalTime / secondInMilliseconds) % 60).toInt()

        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

}
