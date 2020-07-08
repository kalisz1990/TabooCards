package com.example.taboocards.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.taboocards.menu.MenuActivity
import com.example.taboocards.R
import kotlinx.android.synthetic.main.activity_game.*

private val timerSeconds: Long = 3L
private val timerMinutes: Long = 0L

const val secondsInMinuteInMillis: Long = 60000L
const val millisecondsInSeconds: Long = 1000L

class GameActivity : AppCompatActivity() {
    private lateinit var gameService: GameService
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        timer(timerSeconds, timerMinutes)

    }

    fun startTimer() {
        gameService = GameService()
        gameService.startTimer()
    }

    private fun activitySetup() {
        team_1_name_game_activity.text = intent.getStringExtra(getString(R.string.team_1))
        team_2_name_game_activity.text = intent.getStringExtra(getString(R.string.team_2))
        timer_counter_minutes.text = timerMinutes.toString()
        timer_counter_seconds.text = timerSeconds.toString()
        score_team_1_game_activity.text = "0"
        score_team_2_game_activity.text = "0"
    }

    private fun timer(timeInSeconds: Long, timeInMinutes: Long) {
        val totalTime =
            timeInSeconds * millisecondsInSeconds + timeInMinutes * secondsInMinuteInMillis

        countDownTimer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli
                val elapsedSeconds = diff / secondsInMilli
                diff %= secondsInMilli

                timer_counter_minutes.text = elapsedMinutes.toString()
                setTimeCounterSeconds(diff, elapsedSeconds)
            }

            override fun onFinish() {
                countDownTimer.cancel()
            }
        }.start()
    }

    private fun setTimeCounterSeconds(diff: Long, elapsedSeconds: Long) {
        if (diff < 10000) {
            set0BeforeNumberIfSecondsLessThan10(elapsedSeconds - 1, timer_counter_seconds)
        }
        if (0.compareTo(elapsedSeconds - 1) == 0) {
            countDownTimer.cancel()
            Toast.makeText(this, "koniec", Toast.LENGTH_SHORT).show()
            //TODO: jak sie skonczy czas to tutaj wstawić Dialog ze Koniec
        }
    }

    private fun set0BeforeNumberIfSecondsLessThan10(elapsedSeconds: Long, textView: TextView) {
        if (elapsedSeconds < 10) {
            textView.text = "0$elapsedSeconds"
        } else {
            textView.text = elapsedSeconds.toString()
        }
    }

    fun okButton(view: View) {

    }

    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

}