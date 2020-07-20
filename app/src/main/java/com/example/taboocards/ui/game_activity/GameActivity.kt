package com.example.taboocards.ui.game_activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelStoreOwner
import com.example.taboocards.ui.menu_activity.MenuActivity
import com.example.taboocards.R
import com.example.taboocards.data.game.GameDetails.Companion.team1Score
import com.example.taboocards.data.game.GameDetails.Companion.team2Score
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.ext.android.getViewModel

private var totalTime: Long = 0L

class GameActivity : AppCompatActivity(), DialogInterface.OnDismissListener {

    private var fm: FragmentManager = supportFragmentManager
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameViewModel = getViewModel()

        activitySetup()
        openStartDialog()

    }

    private fun openStartDialog() {
        gameViewModel.openStartDialog(fm)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        timerCountdown()
    }

    private fun timerCountdown() {
        gameViewModel.startTimer(totalTime, time_counter)
    }

    private fun activitySetup() {
        team_1_name_game_activity.text = intent.getStringExtra(getString(R.string.team_1))
        team_2_name_game_activity.text = intent.getStringExtra(getString(R.string.team_2))
        totalTime = intent.getLongExtra(getString(R.string.tour_time), 90000L)
        score_team_1_game_activity.text = team1Score.toString()
        score_team_2_game_activity.text = team2Score.toString()
    }

    fun okButton(view: View) {
        var point = gameViewModel.addPoints(team1Score)
        score_team_1_game_activity.text = point.toString()

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
