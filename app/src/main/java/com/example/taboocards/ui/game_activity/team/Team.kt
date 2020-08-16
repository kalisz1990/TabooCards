package com.example.taboocards.ui.game_activity.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_team")
data class Team(
    @ColumnInfo(name = "team_name") var teamName: String?,
    @ColumnInfo(name = "points") var points: Int
) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}