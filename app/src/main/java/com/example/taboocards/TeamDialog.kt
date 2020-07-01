package com.example.taboocards

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class TeamDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_dialog)

    }


}