package com.example.taboocards.ui.game_activity

import android.content.Context
import android.os.AsyncTask
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.taboocards.R
import com.example.taboocards.data.game.GameDetails.Companion.pointsToWinGameDetails
import com.example.taboocards.ui.game_activity.cards.Card
import com.example.taboocards.ui.game_activity.cards.CardGenerator
import com.example.taboocards.ui.game_activity.dialog.DialogCreator
import com.example.taboocards.ui.game_activity.teams.*
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator

class GameViewModel(
    private var timerCoordinator: TimerCoordinator,
    private var dialogCreator: DialogCreator,
    private var teamRepository: TeamRepository,
    private var cardGenerator: CardGenerator,
    private val context: Context
) : ViewModel() {

    fun startTimer(totalTime: Long, textView: TextView, fm: FragmentManager) {
        timerCoordinator.startTimer(totalTime, textView, fm)
    }

    fun stopTimer() {
        timerCoordinator.stopTimer()
    }

    fun openStartDialog(fm: FragmentManager, teamName: String) {
        dialogCreator.createDialog(
            R.layout.custom_dialog_game_activity,
            "$teamName ${context.getString(R.string.get_ready)}",
            fm,
            context.getString(R.string.start)
        )
    }

    //TODO: do menuViewModel?
    fun addTeamToDb(teamName: String?) {
        AsyncTask.execute {
            val newTeam = Team(teamName, 0)
            teamRepository.addTeam(newTeam)
        }
    }

    fun updatePointsInTextView(
        teamName: String?,
        numberOfPoints: Int,
        textView: TextView,
        fm: FragmentManager?
    ) {
        val asyncClass =
            AsyncClassToUpdatePoints(
                teamRepository,
                this,
                teamName,
                numberOfPoints,
                textView,
                fm
            )
        asyncClass.execute()
    }

    fun changeTeam(team1: String, team2: String, currentTeamNr: Int): String {
        return if (currentTeamNr == 1) {
            team1
        } else {
            team2
        }
    }

    fun isGameOver(textView: TextView, pointsToWin: String): Boolean {
        return textView.text == pointsToWin
    }

    fun clearDB() {
        AsyncTask.execute {
            teamRepository.deleteAllTeams()
        }
    }

    fun finishGame(currentTeamName: String?, fm: FragmentManager?) {
        dialogCreator.createDialog(
            R.layout.custom_dialog_game_activity,
            "$currentTeamName ${context.getString(R.string.won)}",
            fm,
            context.getString(R.string.finish)
        )
        timerCoordinator.stopTimer()
    }


    fun skipButton(skipTextView: TextView, teamPoints: TextView, teamName: String?): String {
        var chances: Int = skipTextView.text.toString().toInt()
        return if (chances > 0) {
            chances--
            chances.toString()
        } else {
            updatePointsInTextView(teamName, -1, teamPoints, null)
            "0"
        }
    }


    fun generateCard(): ArrayList<String> {

        val asyncTaskToGetCard = AsyncTaskToGetCard(cardGenerator).execute()
        val card = asyncTaskToGetCard.get()
        val list = arrayListOf<String>()
        list.add(card.mainWord)
        list.add(card.word1)
        list.add(card.word2)
        list.add(card.word3)
        list.add(card.word4)
        list.add(card.word5)
        return list
    }

    private class AsyncClassToUpdatePoints(
        private val teamRepository: TeamRepository,
        private val gameViewModel: GameViewModel,
        private val teamName: String?,
        private val numberOfPoints: Int,
        private val textView: TextView,
        private val fm: FragmentManager?
    ) :
        AsyncTask<String, Void, String>() {

        private var points: String = ""

        override fun doInBackground(vararg params: String?): String {
            updateTeam()
            points = getPoints()
            return points
        }

        override fun onPostExecute(result: String?) {
            updateTextView(textView)
            if (result.toString() == pointsToWinGameDetails) {
                gameViewModel.finishGame(teamName, fm)
            }
        }

        private fun updateTextView(textView: TextView) {
            textView.text = points
        }

        private fun getPoints(): String {
            return teamRepository.getTeam(teamName).points.toString()
        }

        private fun updateTeam() {
            val team: Team = teamRepository.getTeam(teamName)
            team.points += numberOfPoints
            teamRepository.updateTeam(team)
        }
    }

    private class AsyncTaskToGetCard(
        private val cardGenerator: CardGenerator
    ) : AsyncTask<String, Void, Card>() {

        override fun doInBackground(vararg params: String?): Card {
            return cardGenerator.generate()
        }
    }

}
