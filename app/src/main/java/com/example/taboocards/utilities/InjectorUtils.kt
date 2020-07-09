package com.example.taboocards.utilities

import com.example.taboocards.data.card.CardDatabase
import com.example.taboocards.data.card.CardRepository
import com.example.taboocards.ui.game_activity.GameViewModelFactory

object InjectorUtils {

    fun provideCardsViewModelFactory(): GameViewModelFactory {
        val cardRepository = CardRepository
            .getInstance(CardDatabase.getInstance().cardDao)
        return GameViewModelFactory(cardRepository)
    }



}