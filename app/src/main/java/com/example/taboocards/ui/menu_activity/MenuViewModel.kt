package com.example.taboocards.ui.menu_activity

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.taboocards.ui.menu_activity.settings.SettingsDialog
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog

class MenuViewModel(
    private var startGameDialog: StartGameDialog,
    private var settingsDialog: SettingsDialog
) : ViewModel() {

    fun openStartDialog(fm: FragmentManager) {
        startGameDialog = StartGameDialog()
        startGameDialog.show(fm, "start_game_dialog")
    }

    fun openSettingsDialog(fm: FragmentManager) {
        settingsDialog = SettingsDialog()
        settingsDialog.show(fm, "setting_dialog")
    }

}