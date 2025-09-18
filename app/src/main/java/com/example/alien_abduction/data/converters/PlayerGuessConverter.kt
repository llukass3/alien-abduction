package com.example.alien_abduction.data.converters

import androidx.room.TypeConverter
import com.example.alien_abduction.domain.dataModels.PlayerGuess
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PlayerGuessConverter {
    @TypeConverter
    fun fromPlayerGuess(value: PlayerGuess): String = Json.encodeToString(value)

    @TypeConverter
    fun toPlayerGuess(value: String): PlayerGuess = Json.decodeFromString(value)
}