package com.example.taboocards

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

private var team1: String = ""
private var team2: String = ""


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    }

    fun startGame(view: View) {
        teamDetailsDialog()
    }

    private fun teamDetailsDialog() {
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.team_dialog, null)
        val team1EditText = dialogView.findViewById<EditText>(R.id.team_1_name_dialog)
        val team2EditText = dialogView.findViewById<EditText>(R.id.team_2_name_dialog)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Start") { dialogInterface: DialogInterface?, i: Int -> }

        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            when {
                team1EditText.text.toString().isEmpty() -> {
                    Toast.makeText(this, "empty Team 1 name", Toast.LENGTH_SHORT).show()
                }
                team2EditText.text.toString().isEmpty() -> {
                    Toast.makeText(this, "empty Team 2 name", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    team1 = team1EditText.text.toString()
                    team2 = team2EditText.text.toString()
                    customDialog.dismiss()
                    goToGameActivity()
                }
            }
        }

    }

    private fun goToGameActivity() {
        val intent = Intent(this@MenuActivity, GameActivity::class.java)
        intent.putExtra("team1", team1)
        intent.putExtra("team2", team2)
        teamDetailsDialog()
        startActivity(intent)
        finish()
    }

}
