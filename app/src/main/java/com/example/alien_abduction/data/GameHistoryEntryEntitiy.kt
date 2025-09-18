package com.example.alien_abduction.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history")
data class GameHistoryEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val gameMode: String,
    val date: String,
    val timeOfDay: String,
    val playerResultsJson: String // Use a TypeConverter for List<PlayerResult> if needed
)