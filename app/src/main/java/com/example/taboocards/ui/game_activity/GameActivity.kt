package com.example.taboocards.ui.game_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

private var timerSeconds: Long = 5L
private var timerMinutes: Long = 1L

const val minuteInMilliseconds: Long = 60000L
const val secondInMilliseconds: Long = 1000L

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        timerCountdown()


    }

    private fun timerCountdown() {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.startTimer(timerSeconds, timerMinutes, time_counter)
    }

//    private fun initializeUI() {
//        val factory = InjectorUtils.provideCardsViewModelFactory()
//        val viewModel = ViewModelProvider(this, factory)
//            .get(GameViewModel::class.java)
//
//        viewModel.getCards().observe(this, Observer { cards ->
//            val stringBuilder = StringBuilder()
//            cards.forEach { card ->
//                stringBuilder.append("$card\n\n")
//            }
//        })
//    }

    private fun activitySetup() {
        team_1_name_game_activity.text = intent.getStringExtra(getString(R.string.team_1))
        team_2_name_game_activity.text = intent.getStringExtra(getString(R.string.team_2))
//        timer_counter_minutes.text = timerMinutes.toString()
//        timer_counter_seconds.text = timerSeconds.toString()
        score_team_1_game_activity.text = "0"
        score_team_2_game_activity.text = "0"
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