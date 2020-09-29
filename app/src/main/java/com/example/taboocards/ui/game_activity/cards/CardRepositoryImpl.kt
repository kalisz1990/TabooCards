package com.example.taboocards.ui.game_activity.cards

class CardRepositoryImpl(
    private val cardDao: CardDao
) : CardRepository {

    override fun addCard(card: Card) {
        cardDao.addCard(card)
    }

    override fun getCard(id: Int?): Card {
        return cardDao.getCard(id)
    }

    override fun deleteAllCards() {
        cardDao.deleteAllCards()
    }

}