package com.example.taboocards.ui.menu_activity

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog

class MenuViewModel(private var startGameDialog: StartGameDialog) : ViewModel() {

    fun openStartDialog(fragmentManager: FragmentManager) {
        startGameDialog = StartGameDialog()
        startGameDialog.show(fragmentManager,"start_game_dialog")
    }

}