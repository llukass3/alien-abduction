package com.example.alien_abduction.data.converters

import androidx.room.TypeConverter
import com.example.alien_abduction.domain.GameMode

class GameModeConverter {
    @TypeConverter
    fun fromGameMode(value: GameMode): String = value.name

    @TypeConverter
    fun toGameMode(value: String): GameMode = GameMode.valueOf(value)
}