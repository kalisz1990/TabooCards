package com.example.taboocards.ui.game_activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.data.game.GameDetails.Companion.tourTime
import com.example.taboocards.ui.game_activity.team.TeamDatabase
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.concurrent.timer

private var totalTime: Long = 0L
private var team1Name: String = ""
private var team2Name: String = ""
private const val okPoints: Int = 1
private const val wrongPoints: Int = -1
private const val skipChances: Int = 3

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GameActivity : AppCompatActivity(), DialogInterface.OnDismissListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        openStartDialog()
        initializeRoom()
        addTeamsToDB()

    }

    private fun openStartDialog() {
        val fm = supportFragmentManager
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.openStartDialog(fm)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        timerCountdown()
    }

    private fun changeTeam(){
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.changeTeam()
    }


    private fun timerCountdown() {
        val fm = supportFragmentManager
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.startTimer(totalTime, time_counter, fm)
    }

    private fun activitySetup() {
        team1Name = intent.getStringExtra(getString(R.string.team_1))
        team2Name = intent.getStringExtra(getString(R.string.team_2))
        team_1_name_game_activity.text = team1Name
        team_2_name_game_activity.text = team2Name
        totalTime = intent.getLongExtra(getString(R.string.tour_time), tourTime)
        skip_chances_textView_GameActivity_numbers.text = skipChances.toString()
    }

    fun okButton(view: View) {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.updatePointsInTextView(team1Name, okPoints, score_team_1_game_activity)
    }

    fun wrongButton(view: View) {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.updatePointsInTextView(team1Name, wrongPoints, score_team_1_game_activity)
    }

    fun skipButton(view: View) {
        val gameViewModel = getViewModel<GameViewModel>()
        skip_chances_textView_GameActivity_numbers.text =
            gameViewModel.skipButton(
                skip_chances_textView_GameActivity_numbers,
                score_team_1_game_activity,
                team1Name
            )
    }


    private fun initializeRoom() {
        val db = Room.databaseBuilder(
            applicationContext,
            TeamDatabase::class.java, "table_score"
        ).build()
    }

    private fun addTeamsToDB() {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.addTeamToDb(team1Name)
        gameViewModel.addTeamToDb(team2Name)
    }


    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}
