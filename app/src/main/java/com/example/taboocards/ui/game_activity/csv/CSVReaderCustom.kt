package com.example.taboocards.ui.game_activity.csv

import android.content.Context
import com.example.taboocards.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import kotlin.collections.ArrayList


class CSVReaderCustom(val context: Context) {

    fun readToArray(): ArrayList<String> {
        val cards: ArrayList<String> = ArrayList()
        val inputStream: InputStream = context.resources.openRawResource(R.raw.data)
        val bufferedReader = BufferedReader(
            InputStreamReader(
                inputStream,
                Charset.forName("UTF-8")
            )
        )
        for (line in bufferedReader.readLines()) {
            cards.add(line)
        }
        return cards
    }


}