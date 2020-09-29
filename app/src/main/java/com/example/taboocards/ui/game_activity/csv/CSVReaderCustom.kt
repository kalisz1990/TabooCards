package com.example.taboocards.ui.game_activity.csv

import android.content.Context
import android.content.res.AssetManager
import android.os.AsyncTask
import com.example.taboocards.R
import com.example.taboocards.ui.game_activity.cards.Card
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList


class CSVReaderCustom(val context: Context) {

    val cards: ArrayList<CardExample> = ArrayList()

    fun read():ArrayList<CardExample> {

            val inputStream: InputStream = context.resources.openRawResource(R.raw.data)
            val bufferedReader = BufferedReader(
                InputStreamReader(
                    inputStream,
                    Charset.forName("UTF-8")
                )
            )
            var stringTokenizer: StringTokenizer

            for (line in bufferedReader.readLines()) {
                stringTokenizer = StringTokenizer(line, ";")

                val card = CardExample()

                card.cardId = stringTokenizer.nextToken()
                card.mainWord = stringTokenizer.nextToken()
                card.s1 = stringTokenizer.nextToken()
                card.s2 = stringTokenizer.nextToken()
                card.s3 = stringTokenizer.nextToken()
                card.s4 = stringTokenizer.nextToken()
                card.s5 = stringTokenizer.nextToken()

                cards.add(card)
            }

        return cards
    }
}