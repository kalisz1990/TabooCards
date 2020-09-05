package com.example.taboocards.ui.game_activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.room.Room
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.data.game.GameDetails.Companion.tourTime
import com.example.taboocards.ui.game_activity.team.TeamDatabase
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

private var totalTime: Long = 0L
private var team1Name: String = ""
private var team2Name: String = ""
private var currentTeamNr: Int = 1
private var currentTeamName: String = team1Name
private lateinit var currentScoreTextView: TextView

private const val okPoints: Int = 1
private const val wrongPoints: Int = -1
private const val skipChances: Int = 3

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GameActivity : AppCompatActivity(), DialogInterface.OnDismissListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activitySetup()
        initializeRoom()
        clearDatabase()
        openStartDialog()
        addTeamsToDB()
    }

    private fun openStartDialog() {
        val fm = supportFragmentManager
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.openStartDialog(fm)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        timerCountdown()
        changeTeam()
    }

    private fun changeTeam() {
        val gameViewModel = getViewModel<GameViewModel>()
        currentTeamName = gameViewModel.changeTeam(team1Name, team2Name, currentTeamNr)
        if (currentTeamNr == 1) {
            currentTeamNr = 0
            currentScoreTextView = score_team_1_game_activity
            skip_chances_textView_GameActivity_numbers.text = skipChances.toString()
        } else {
            currentTeamNr = 1
            currentScoreTextView = score_team_2_game_activity
            skip_chances_textView_GameActivity_numbers.text = skipChances.toString()
        }
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
        currentScoreTextView = score_team_1_game_activity
    }

    fun okButton(view: View) {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.updatePointsInTextView(currentTeamName, okPoints, currentScoreTextView)
    }

    fun wrongButton(view: View) {
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.updatePointsInTextView(
            currentTeamName,
            wrongPoints,
            currentScoreTextView
        )
    }

    fun skipButton(view: View) {
        val gameViewModel = getViewModel<GameViewModel>()
        skip_chances_textView_GameActivity_numbers.text =
            gameViewModel.skipButton(
                skip_chances_textView_GameActivity_numbers,
                currentScoreTextView,
                currentTeamName
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

    private fun clearDatabase(){
        val gameViewModel = getViewModel<GameViewModel>()
        gameViewModel.clearDB()

    }


    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}
