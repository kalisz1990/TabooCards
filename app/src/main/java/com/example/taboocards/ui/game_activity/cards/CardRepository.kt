package com.example.taboocards.ui.game_activity.cards

interface CardRepository {

    fun addCard(card: Card)

    fun getCard(id: String): Card

    fun deleteAllCards()
}