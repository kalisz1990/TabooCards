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
import kotlinx.android.synthetic.main.custom_dialog_game_activity.view.*

class CustomSimpleGameDialog(
    private val layoutR: Int,
    private val text: String?,
    private val buttonName: String?
) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(layoutR, container, false)
        val button = rootView.findViewById<Button>(R.id.button_game_activity_dialog)
        rootView.custom_dialog_textView.text = text
        button.text = buttonName

        button.setOnClickListener {
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
        alertDialog.setView(layoutR)
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