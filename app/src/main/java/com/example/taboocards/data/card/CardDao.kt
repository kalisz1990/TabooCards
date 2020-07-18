package com.example.taboocards.data.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CardDao {

    private val cardList = mutableListOf<Card>()
    private val cards = MutableLiveData<List<Card>>()

    init {
        cards.value = cardList
    }

    fun addCard(card: Card) {
        cardList.add(card)
        cards.value = cardList
    }

    fun getCards() = cards as LiveData<List<Card>>

}
