package com.example.taboocards.ui.menu_activity.settings

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
import kotlinx.android.synthetic.main.dialog_settings.view.*
import kotlinx.android.synthetic.main.start_game_dialog.*
import java.util.*
import java.util.concurrent.TimeUnit

private var team1 = ""
private var team2 = ""
private var tourTimeInMillis = 150000L
private var pointsToWin = 0L

const val timerStep = 10000L

class SettingsDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.dialog_settings, container, false)
        val timerSettingsDialog = rootView.findViewById<TextView>(R.id.timer_settings_dialog)

        val team1EditText = rootView.findViewById<EditText>(R.id.team1_name_settings_editText)
        val team2EditText = rootView.findViewById<EditText>(R.id.team2_name_settings_editText)

        val plusButton = rootView.findViewById<Button>(R.id.plus_button_settings_dialog)
        val minusButton = rootView.findViewById<Button>(R.id.minus_button_settings_dialog)
        val saveAndSetButton = rootView.findViewById<Button>(R.id.save_and_set_button_settings)

        plusButton.setOnClickListener {
            tourTimeInMillis += timerStep
            updateTourTimer(tourTimeInMillis, timerSettingsDialog)
        }

        minusButton.setOnClickListener {
            tourTimeInMillis -= timerStep
            updateTourTimer(tourTimeInMillis, timerSettingsDialog)
        }

        saveAndSetButton.setOnClickListener {
            team1 = team1EditText.text.toString()
            team2 = team2EditText.text.toString()

            setDetails()
            dismiss()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window!!.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setView(R.layout.dialog_settings)
        alertDialog.create()
        alertDialog.setCancelable(false)
        return alertDialog.show()
    }

    private fun setDetails() {
        val intent = Intent(requireContext(), GameActivity::class.java)
        intent.putExtra(
            "team1",
            team1
        )
        intent.putExtra(
            "team2",
            team2
        )
        intent.putExtra(
            "tour_time",
            tourTimeInMillis
        )

    }

    private fun updateTourTimer(timer: Long, textView: TextView) {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timer)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timer)
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textView.text = timeLeftFormatted

    }
}