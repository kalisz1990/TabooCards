package com.example.taboocards.ui.game_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taboocards.data.card.CardRepository

class GameViewModelFactory(private val cardRepository: CardRepository): ViewModelProvider.NewInstanceFactory() {


    //TODO: doczytac o annotaciach @Supress
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel(cardRepository) as T
    }
}