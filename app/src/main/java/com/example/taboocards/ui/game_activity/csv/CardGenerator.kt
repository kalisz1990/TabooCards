package com.example.taboocards.ui.game_activity.csv

import android.content.Context
import java.util.ArrayList
import kotlin.random.Random

class CardGenerator(val context: Context) {

    val cards = CSVReaderCustom(context).read()

    fun generate(): CardExample {
        return cards[Random.nextInt(1, 2669)]
    }
}