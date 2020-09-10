package com.example.taboocards.data.card

interface CardRepository {

    fun addCard(card: Card)

    fun getCard(id: Int?): Card

    fun deleteAllCards()
}