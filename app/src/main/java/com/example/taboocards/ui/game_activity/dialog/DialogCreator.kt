package com.example.taboocards.ui.game_activity.dialog

import androidx.fragment.app.FragmentManager

class DialogCreator {

    fun createDialog(layoutR: Int, text: String?, fm: FragmentManager) {
        CustomSimpleGameDialog(layoutR, text).show(fm, "custom_simple_dialog")
    }
}