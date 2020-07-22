package com.example.taboocards.ui.game_activity.score

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_score")
data class Score(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "team_name") val teamName: String,
    @ColumnInfo(name = "points") val points: Int
)