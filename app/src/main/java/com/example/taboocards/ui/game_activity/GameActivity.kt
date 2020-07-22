package com.example.taboocards.ui.game_activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.ui.game_activity.score.ScoreDatabase
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

private var totalTime: Long = 0L

class GameActivity : AppCompatActivity(), DialogInterface.OnDismissListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        openStartDialog()
        initializeRoom()

    }

    private fun openStartDialog() {
        val fm = supportFragmentManager
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.openStartDialog(fm)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        timerCountdown()
    }

    private fun timerCountdown() {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.startTimer(totalTime, time_counter)
    }

    private fun activitySetup() {
        team_1_name_game_activity.text = intent.getStringExtra(getString(R.string.team_1))
        team_2_name_game_activity.text = intent.getStringExtra(getString(R.string.team_2))
        totalTime = intent.getLongExtra(getString(R.string.tour_time), 90000L)
        score_team_1_game_activity.text = "0"
        score_team_2_game_activity.text = "0"
    }

    fun okButton(view: View) {


    }

    fun wrongButton(view: View) {

    }

    fun skipButton(view: View) {

    }

    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun initializeRoom(){
        val db = Room.databaseBuilder(
            applicationContext,
            ScoreDatabase::class.java, "table_score"
        ).build()
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

}
