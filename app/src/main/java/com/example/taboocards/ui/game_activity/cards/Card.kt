package com.example.taboocards.ui.game_activity.cards

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_cards")
data class Card(
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "main_word") var mainWord: String,
    @ColumnInfo(name = "s1") var word1: String,
    @ColumnInfo(name = "s2") var word2: String,
    @ColumnInfo(name = "s3") var word3: String,
    @ColumnInfo(name = "s4") var word4: String,
    @ColumnInfo(name = "s5") var word5: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}