package com.example.taboocards.ui.game_activity.team

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun scoreDao(): TeamDao

    companion object {

        @Volatile
        private var instance: TeamDatabase? = null

        fun getInstance(context: Context): TeamDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            TeamDatabase::class.java, "todo-list.db"
        ).build()
    }

}
