package com.example.taboocards.ui.game_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

private var timerSeconds: Long = 5L
private var timerMinutes: Long = 1L

const val minuteInMilliseconds: Long = 60000L
const val secondInMilliseconds: Long = 1000L

class GameActivity : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        timer(timerSeconds, timerMinutes)
    }

    private fun initializeUI() {
        val factory = InjectorUtils.provideCardsViewModelFactory()
        val viewModel = ViewModelProvider(this, factory)
            .get(GameViewModel::class.java)

        viewModel.getCards().observe(this, Observer { cards ->
            val stringBuilder = StringBuilder()
            cards.forEach { card ->
                stringBuilder.append("$card\n\n")
            }
        })
    }

    private fun activitySetup() {
        team_1_name_game_activity.text = intent.getStringExtra(getString(R.string.team_1))
        team_2_name_game_activity.text = intent.getStringExtra(getString(R.string.team_2))
//        timer_counter_minutes.text = timerMinutes.toString()
//        timer_counter_seconds.text = timerSeconds.toString()
        score_team_1_game_activity.text = "0"
        score_team_2_game_activity.text = "0"
    }

    private fun timer(timeInSeconds: Long, timeInMinutes: Long) {
        var totalTime =
            (timeInSeconds * secondInMilliseconds) + (timeInMinutes * minuteInMilliseconds)

        countDownTimer = object : CountDownTimer(totalTime, 500) {
            override fun onTick(millisUntilFinished: Long) {
                totalTime = millisUntilFinished
                updateCountDownText(totalTime)
            }

            override fun onFinish() {
                Toast.makeText(this@GameActivity, "finish", Toast.LENGTH_SHORT).show()
                countDownTimer.cancel()
            }
        }.start()
    }

    private fun updateCountDownText(totalTime: Long) {
        val minutes: Int = ((totalTime / 1000) / 60).toInt();
        val seconds: Int = ((totalTime / 1000) % 60).toInt();
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        time_counter.text = timeLeftFormatted
    }

    fun okButton(view: View) {

    }

    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun wrongButton(view: View) {}
    fun skipButton(view: View) {}
}