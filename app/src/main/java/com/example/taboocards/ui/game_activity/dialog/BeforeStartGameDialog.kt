package com.example.taboocards.ui.game_activity.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.taboocards.R

class BeforeStartGameDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.start_dialog_game_activity, container, false)
        val startButton = rootView.findViewById<Button>(R.id.start_button_game_activity_dialog)

        startButton.setOnClickListener {
            dismiss()
        }

        dialog?.setCanceledOnTouchOutside(false)
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
        alertDialog.setView(R.layout.start_dialog_game_activity)
        alertDialog.create()
        alertDialog.setCancelable(false)
        return alertDialog.show()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity: FragmentActivity? = activity
        if (activity is DialogInterface.OnDismissListener) {
            activity.onDismiss(dialog)
        }

    }

}