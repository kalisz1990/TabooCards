package com.example.taboocards.data.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(card: Card)

    @Query("SELECT * FROM table_cards WHERE id = :id")
    fun getCard(id: Int?): Card

    @Query("DELETE FROM table_cards")
    fun deleteAllCards()
}
