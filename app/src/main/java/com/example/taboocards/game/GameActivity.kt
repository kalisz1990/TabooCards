package com.example.taboocards.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.taboocards.menu.MenuActivity
import com.example.taboocards.R
import kotlinx.android.synthetic.main.activity_game.*

const val team_1_name_game: String = ""
const val team_2_name_game: String = ""
const val team_1_score_game: Int = 0
const val team_2_score_game: Int = 0

private var timerSeconds: Long = 12L
private var timerMinutes: Long = 1L

const val secondsInMinuteInMillis: Long = 60000L

class GameActivity : AppCompatActivity() {
    private lateinit var gameService: GameService
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        timer()

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

    fun timer() {
        val timeCounter: Long = (timerSeconds * 1000L) + (timerMinutes * secondsInMinuteInMillis)

        for (x in timerMinutes downTo -1 step 1) {
            timer = object : CountDownTimer(timeCounter, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeLeft = if (millisUntilFinished <= secondsInMinuteInMillis) {
                        millisUntilFinished / 1000L
                    } else {
                        millisUntilFinished > secondsInMinuteInMillis && millisUntilFinished < secondsInMinuteInMillis * 2 -> timerMinutes++
                    }
                    when {
                        timerSeconds < 10 -> {
                            timer_counter_seconds.text = "0$timeLeft"
                        }
                        timerSeconds < 1 -> {
                            timer_counter_seconds.text = "00"
                            timer_counter_minutes.text = timerMinutes.toString()
                            timerSeconds = secondsInMinuteInMillis / 1000L
                        }
                        else -> {
                            timer_counter_seconds.text = timeLeft.toString()
                        }
                    }
                }

                override fun onFinish() {

                }
            }.start()
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