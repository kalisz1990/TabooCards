package com.example.taboocards.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.taboocards.menu.MenuActivity
import com.example.taboocards.R
import kotlinx.android.synthetic.main.activity_game.*

private var timerSeconds: Long = 10L
private var timerMinutes: Long = 0L

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

    fun activitySetup() {
        team_1_name_game_activity.text = intent.getStringExtra(getString(R.string.team_1))
        team_2_name_game_activity.text = intent.getStringExtra(getString(R.string.team_2))
        timer_counter_minutes.text = timerMinutes.toString()
        timer_counter_seconds.text = timerSeconds.toString()
        score_team_1_game_activity.text = "0"
        score_team_2_game_activity.text = "0"
    }

    fun timer(timeInSeconds: Long, timeInMinutes: Long) {
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

                timer_counter_minutes.text = elapsedMinutes.toString()

                when {
                    diff < 10000 -> {
                        timer_counter_seconds.text = "0$elapsedSeconds"
                    }
                    diff < 2000 -> {
                        timer_counter_seconds.text = "00"
                    }
                    else -> {
                        timer_counter_seconds.text = elapsedSeconds.toString()
                    }
                }

            }


            override fun onFinish() {
                Toast.makeText(this@GameActivity, "time over", Toast.LENGTH_SHORT).show()
                countDownTimer.cancel()
                timer_counter_seconds.text = timeInSeconds.toString()
                timer_counter_minutes.text = timeInMinutes.toString()
            }
        }.start()
    }


    fun okButton(view: View) {
        timer(timerSeconds, timerMinutes)
    }


    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

}