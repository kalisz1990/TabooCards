package com.example.taboocards.ui.game_activity

import android.os.AsyncTask
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.taboocards.ui.game_activity.dialog.BeforeStartGameDialog
import com.example.taboocards.ui.game_activity.team.*
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator

private var tempPoints: Int = 0

class GameViewModel(
    private var timerCoordinator: TimerCoordinator,
    private var beforeStartGameDialog: BeforeStartGameDialog,
    private var teamRepository: TeamRepository
) : ViewModel() {

    fun startTimer(totalTime: Long, textView: TextView) {
        timerCoordinator.startTimer(totalTime, textView)
    }

    fun openStartDialog(fm: FragmentManager) {
        beforeStartGameDialog = BeforeStartGameDialog()
        beforeStartGameDialog.show(fm, "before_start_game_dialog")
    }

    fun addTeamToDb(teamName: String?) {
        AsyncTask.execute {
            val newTeam = Team(teamName, 0)
            teamRepository.addTeam(newTeam)
        }
    }

    fun updatePointsInTextView(teamName: String?, numberOfPoints: Int, textView: TextView) {
        val asyncClass = AsyncClassToUpdatePoints(teamRepository, teamName, numberOfPoints, textView)
        asyncClass.execute()

    }

    fun skipButton() {



    }



    private class AsyncClassToUpdatePoints(
        private val teamRepository: TeamRepository,
        private val teamName: String?,
        private val numberOfPoints: Int,
        private val textView: TextView
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


}
