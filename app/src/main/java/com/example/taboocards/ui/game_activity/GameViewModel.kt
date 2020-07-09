package com.example.taboocards.ui.game_activity

import androidx.lifecycle.ViewModel
import com.example.taboocards.data.card.CardRepository

class GameViewModel(private val cardRepository: CardRepository):ViewModel() {

    fun getCards() = cardRepository.getCards()

    //TODO: get only 1 random carc

}