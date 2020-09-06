package com.example.taboocards.ui.game_activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.data.game.GameDetails.Companion.pointsToWinGameDetails
import com.example.taboocards.data.game.GameDetails.Companion.tourTimeGameDetails
import com.example.taboocards.ui.game_activity.team.TeamDatabase
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

private var totalTime: Long = 0L
private var pointsToWin: String = ""
private var team1Name: String = ""
private var team2Name: String = ""
private var currentTeamNr: Int = 1
private var currentTeamName: String = ""
private lateinit var currentScoreTextView: TextView

private const val okPoints: Int = 1
private const val wrongPoints: Int = -1
private const val skipChances: Int = 3

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GameActivity : AppCompatActivity(), DialogInterface.OnDismissListener {

    private var fm: FragmentManager = supportFragmentManager
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameViewModel = getViewModel()

        firstActivitySetup()
        initializeRoom()
        clearDatabase()
        openStartDialog()
        addTeamsToDB()
    }

    private fun firstActivitySetup() {
        team1Name = intent.getStringExtra(getString(R.string.team_1))
        team2Name = intent.getStringExtra(getString(R.string.team_2))
        team_1_name_game_activity.text = team1Name
        team_2_name_game_activity.text = team2Name
        totalTime = intent.getLongExtra(getString(R.string.tour_time), tourTimeGameDetails)
        pointsToWin = intent.getStringExtra(getString(R.string.points_to_win))
        skip_chances_textView_GameActivity_numbers.text = skipChances.toString()
        currentScoreTextView = score_team_1_game_activity
//        currentTeamName = team1Name
    }

    private fun openStartDialog() {
        gameViewModel.openStartDialog(fm, currentTeamName)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        timerCountdown()
        if (isGameOver()) {
            onBackPressed()
        } else {
            changeTeam()
        }
    }

    private fun timerCountdown() {
        if (isGameOver()) {
            gameViewModel.stopTimer()
        } else {
            gameViewModel.startTimer(totalTime, time_counter, fm)
        }
    }

    private fun changeTeam() {
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

    private fun isGameOver(): Boolean {
        return gameViewModel.isGameOver(currentScoreTextView, pointsToWinGameDetails)
    }

    private fun finishGame() {
        gameViewModel.finishGame(currentTeamName, fm)
    }

    //TODO: zeby otwierało dialog w momencie zdobycia ostatniego punktu
    fun okButton(view: View) {
        if (!isGameOver()) {
            gameViewModel.updatePointsInTextView(currentTeamName, okPoints, currentScoreTextView)
        } else {
            finishGame()
        }
    }

    fun wrongButton(view: View) {
        gameViewModel.updatePointsInTextView(
            currentTeamName,
            wrongPoints,
            currentScoreTextView
        )
    }

    fun skipButton(view: View) {
        skip_chances_textView_GameActivity_numbers.text =
            gameViewModel.skipButton(
                skip_chances_textView_GameActivity_numbers,
                currentScoreTextView,
                currentTeamName
            )
    }

    //TODO: move this function to MenuActivity?
    private fun initializeRoom() {
        Room.databaseBuilder(
            applicationContext,
            TeamDatabase::class.java, "table_score"
        ).build()
    }

    private fun addTeamsToDB() {
        gameViewModel.addTeamToDb(team1Name)
        gameViewModel.addTeamToDb(team2Name)
    }

    //TODO: move this function to MenuActivity?
    private fun clearDatabase() {
        gameViewModel.clearDB()
    }


    override fun onBackPressed() {
        val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}
