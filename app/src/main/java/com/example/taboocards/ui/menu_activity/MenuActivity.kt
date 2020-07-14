package com.example.taboocards.ui.menu_activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taboocards.R
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    }

    fun startGameButton(view: View) {
        val menuViewModel = getViewModel<MenuViewModel>()
        val fm = supportFragmentManager
        menuViewModel.openStartDialog(fm)
    }

    fun settingsButton(view: View) {
        val menuViewModel = getViewModel<MenuViewModel>()
        val fm = supportFragmentManager
        menuViewModel.openSettingsDialog(fm)
    }

}
