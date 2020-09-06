package com.example.taboocards.ui.game_activity.dialog

import androidx.fragment.app.FragmentManager

class DialogCreator {

    fun createDialog(layoutR: Int, msg: String?, fm: FragmentManager, buttonName: String) {
        CustomSimpleGameDialog(layoutR, msg, buttonName).show(fm, "custom_simple_dialog")
    }
}