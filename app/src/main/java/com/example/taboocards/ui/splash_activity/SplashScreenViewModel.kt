package com.example.taboocards.ui.splash_activity

import android.os.AsyncTask
import androidx.lifecycle.ViewModel
import com.example.taboocards.ui.game_activity.cards.Card
import com.example.taboocards.ui.game_activity.cards.CardRepository
import com.example.taboocards.ui.game_activity.csv.CSVReaderCustom
import java.util.*

class SplashScreenViewModel(
    private var cardRepository: CardRepository,
    private var csvReaderCustom: CSVReaderCustom
) : ViewModel() {


    fun addCardsToDb() {
        AsyncTask.execute {
            val stringArray = csvReaderCustom.readToArray()
            for (str in stringArray) {
                val card = createCardFromString(str)
                cardRepository.addCard(card)
            }
        }
    }

    private fun createCardFromString(card: String): Card {
        val stringTokenizer = StringTokenizer(card, ";")

        return Card(
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken()
        )
    }
}