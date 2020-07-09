package com.example.taboocards.ui.menu_activity.start_game

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.taboocards.R
import com.example.taboocards.ui.game_activity.GameActivity
import kotlinx.android.synthetic.main.start_game_dialog.*

private var team1: String = ""
private var team2: String = ""

class StartGameDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.start_game_dialog, container, false)
        val startButton = rootView.findViewById<Button>(R.id.start_button_start_game_dialog)
        val returnButton = rootView.findViewById<Button>(R.id.return_button_start_game_dialog)
        val difficultRadioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)
        val team1EditText = rootView.findViewById<EditText>(R.id.team_1_name_dialog)
        val team2EditText = rootView.findViewById<EditText>(R.id.team_2_name_dialog)

        returnButton.setOnClickListener {
            dismiss()
        }

        startButton.setOnClickListener {
            val selectedId = difficultRadioGroup.checkedRadioButtonId
            val selectedRadioButton = rootView.findViewById<RadioButton>(selectedId)
            //TODO: wybranie poziomu trudności

            when {
                team1EditText.text.isEmpty() -> {
                    Toast.makeText(requireContext(), "empty Team 1 name", Toast.LENGTH_SHORT).show()
                }
                team2EditText.text.isEmpty() -> {
                    Toast.makeText(requireContext(), "empty Team 2 name", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    team1 = team1EditText.text.toString()
                    team2 = team2EditText.text.toString()
                    dismiss()
                    goToGameActivity()
                }
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team_1_name_dialog.requestFocus()
        dialog!!.window!!.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setView(R.layout.start_game_dialog)
        alertDialog.create()
        alertDialog.setCancelable(false)
        return alertDialog.show()
    }

    private fun goToGameActivity() {
        val intent = Intent(requireContext(), GameActivity::class.java)
        intent.putExtra("team1",
            team1
        )
        intent.putExtra("team2",
            team2
        )
        startActivity(intent)
    }

}