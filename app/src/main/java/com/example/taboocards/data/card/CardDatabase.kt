package com.example.taboocards.data.card

class CardDatabase private constructor() {

    var cardDao = CardDao()
        private set

    //TODO: co to jest companion object - doczytać
    companion object {
        @Volatile
        private var instances: CardDatabase? = null

        fun getInstance() =
            instances ?: synchronized(this) {
                instances
                    ?: CardDatabase()
                        .also { instances = it }
            }
    }
}