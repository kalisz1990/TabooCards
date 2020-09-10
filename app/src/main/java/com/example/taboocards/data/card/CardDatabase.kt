package com.example.taboocards.data.card

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {

        @Volatile
        private var instance: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CardDatabase::class.java, "cards-list.db"
        ).build()
    }

}