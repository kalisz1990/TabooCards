package com.example.taboocards.ui.menu_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taboocards.R
import com.example.taboocards.ui.game_activity.GameActivity
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog


private var team1: String = ""
private var team2: String = ""

class MenuActivity : AppCompatActivity() {

    private lateinit var startGameDialog: StartGameDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    }

    fun startGameButton(view: View) {
        showStartGameDialog()
    }


    private fun showStartGameDialog() {
        val fm = supportFragmentManager
        startGameDialog =
            StartGameDialog()
        startGameDialog.show(fm, "start_game_dialog")


    }


    private fun goToGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("team1", team1)
        intent.putExtra("team2", team2)
        startActivity(intent)
        finish()
    }

}
