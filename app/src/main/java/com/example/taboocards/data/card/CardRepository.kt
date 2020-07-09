package com.example.taboocards.data.card

class CardRepository private constructor(private val cardDao: CardDao) {

    fun addCard(card: Card) {
        cardDao.addCard(card)
    }

    fun getCards() = cardDao.getCards()

    companion object {
        @Volatile
        private var instances: CardRepository? = null

        fun getInstance(cardDao: CardDao) =
            instances
                ?: synchronized(this) {
                instances
                    ?: CardRepository(cardDao)
                        .also { instances = it }
            }
    }
}