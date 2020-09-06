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
import com.example.taboocards.data.game.GameDetails.Companion.pointsToWinGameDetails
import com.example.taboocards.data.game.GameDetails.Companion.team1GameDetails
import com.example.taboocards.data.game.GameDetails.Companion.team2GameDetails
import com.example.taboocards.data.game.GameDetails.Companion.tourTimeGameDetails
import com.example.taboocards.ui.game_activity.timer.minuteInMilliseconds
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog
import java.util.*
import java.util.concurrent.TimeUnit

const val timerStep = 10000L

class SettingsDialog : DialogFragment() {

    //TODO: przydałoby sie zrobić porzadki

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.settings_dialog, container, false)
        val timerSettingsDialog = rootView.findViewById<TextView>(R.id.timer_settings_dialog)

        val team1EditText = rootView.findViewById<EditText>(R.id.team1_name_settings_editText)
        val team2EditText = rootView.findViewById<EditText>(R.id.team2_name_settings_editText)

        val plusButton = rootView.findViewById<Button>(R.id.plus_button_settings_dialog)
        val minusButton = rootView.findViewById<Button>(R.id.minus_button_settings_dialog)
        val saveAndSetButton = rootView.findViewById<Button>(R.id.save_and_set_button_settings)

        val pointsToWinEditText =
            rootView.findViewById<EditText>(R.id.points_to_win_settings_dialog)

        updateTourTimer(tourTimeGameDetails, timerSettingsDialog)
        pointsToWinEditText.setText(pointsToWinGameDetails)

        plusButton.setOnClickListener {
            if (tourTimeGameDetails <= minuteInMilliseconds * 4) {
                tourTimeGameDetails += timerStep
                updateTourTimer(tourTimeGameDetails, timerSettingsDialog)
            }
        }

        minusButton.setOnClickListener {
            if (tourTimeGameDetails > 0) {
                tourTimeGameDetails -= timerStep
                updateTourTimer(tourTimeGameDetails, timerSettingsDialog)
            }
        }

        saveAndSetButton.setOnClickListener {
            team1GameDetails = team1EditText.text.toString()
            team2GameDetails = team2EditText.text.toString()

            pointsToWinGameDetails = pointsToWinEditText.text.toString()

            setAndSaveDetails()
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
        alertDialog.setView(R.layout.settings_dialog)
        alertDialog.create()
        alertDialog.setCancelable(false)
        return alertDialog.show()
    }

    private fun updateTourTimer(timeInMillis: Long, textView: TextView) = if (timeInMillis >= 0) {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillis)
        val seconds =
            TimeUnit.MILLISECONDS.toSeconds(timeInMillis - (minutes * minuteInMilliseconds))
        val timeLeftFormatted =
            String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        textView.text = timeLeftFormatted
    } else {
        Toast.makeText(activity, "time cannot be less than 00:00", Toast.LENGTH_SHORT).show()
    }

    private fun setAndSaveDetails() {
        val intent = Intent(requireContext(), StartGameDialog::class.java)
        intent.putExtra(
            getString(R.string.team_1),
            team1GameDetails
        )

        intent.putExtra(
            getString(R.string.team_2),
            team2GameDetails
        )

        intent.putExtra(
            getString(R.string.tour_time),
            tourTimeGameDetails
        )

        intent.putExtra(
            getString(R.string.points_to_win),
            pointsToWinGameDetails
        )
    }
}