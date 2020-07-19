package com.example.taboocards.ui.game_activity

import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.taboocards.ui.game_activity.dialog.BeforeStartGameDialog
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog

class GameViewModel(
    private var timerCoordinator: TimerCoordinator,
    private var beforeStartGameDialog: BeforeStartGameDialog
) : ViewModel() {


//    fun getCards() = cardRepository.getCards()
//    //TODO: get only 1 random carc


    fun startTimer(totalTime: Long, textView: TextView) {
        timerCoordinator.startTimer(totalTime, textView)
    }

    fun openStartDialog(fm: FragmentManager) {
        beforeStartGameDialog = BeforeStartGameDialog()
        beforeStartGameDialog.show(fm, "before_start_game_dialog")
    }

}
