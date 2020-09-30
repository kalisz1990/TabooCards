package com.example.taboocards.ui.game_activity.cards

import kotlin.random.Random

class CardGenerator(
    private var cardRepository: CardRepository
) {

    fun generate(): Card {
        val id = Random.nextInt(1, 2669).toString()
        return cardRepository.getCard(id)
    }

}