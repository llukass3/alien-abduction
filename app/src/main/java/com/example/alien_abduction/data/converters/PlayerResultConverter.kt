package com.example.alien_abduction.data.converters

import androidx.room.TypeConverter
import com.example.alien_abduction.domain.dataModels.PlayerResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PlayerResultConverter {
    @TypeConverter
    fun fromPlayerResult(value: PlayerResult): String = Json.encodeToString(value)

    @TypeConverter
    fun toPlayerResult(value: String): PlayerResult = Json.decodeFromString(value)
}