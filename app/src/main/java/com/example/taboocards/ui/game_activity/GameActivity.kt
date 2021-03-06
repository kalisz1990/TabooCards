package com.example.taboocards.ui.game_activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.data.game.GameDetails.Companion.pointsToWinGameDetails
import com.example.taboocards.data.game.GameDetails.Companion.tourTimeGameDetails
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

private var totalTime: Long = 0L
private var pointsToWin: String = ""
private var team1Name: String = ""
private var team2Name: String = ""

private const val okPoints: Int = 1
private const val wrongPoints: Int = -1
private const val skipChances: Int = 3

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GameActivity : AppCompatActivity(), DialogInterface.OnDismissListener {

    //TODO: jak czas minie a app jest w fazie po wcisnięciu kwadrata, to wywala błąd

    private var fm: FragmentManager = supportFragmentManager
    private lateinit var gameViewModel: GameViewModel
    private lateinit var currentScoreTextView: TextView
    private var currentTeamNr: Int = 1
    private var currentTeamName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameViewModel = getViewModel()

        firstActivitySetup()
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
        currentTeamName = team1Name
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
        generateCard()
    }

    private fun isGameOver(): Boolean {
        return gameViewModel.isGameOver(currentScoreTextView, pointsToWinGameDetails)
    }

    fun okButton(view: View) {
        if (!isGameOver()) {
            gameViewModel.updatePointsInTextView(
                currentTeamName,
                okPoints,
                currentScoreTextView,
                fm
            )
            generateCard()
        }
        if (isGameOver()) {
            gameViewModel.stopTimer()
        }
    }

    fun wrongButton(view: View) {
        gameViewModel.updatePointsInTextView(
            currentTeamName,
            wrongPoints,
            currentScoreTextView,
            fm
        )
        generateCard()
    }

    fun skipButton(view: View) {
        skip_chances_textView_GameActivity_numbers.text =
            gameViewModel.skipButton(
                skip_chances_textView_GameActivity_numbers,
                currentScoreTextView,
                currentTeamName
            )
        generateCard()
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

    private fun generateCard() {
        val list = gameViewModel.generateCard()
        main_word.text = list[0]
        suggestion1.text = list[1]
        suggestion2.text = list[2]
        suggestion3.text = list[3]
        suggestion4.text = list[4]
        suggestion5.text = list[5]
    }
}
