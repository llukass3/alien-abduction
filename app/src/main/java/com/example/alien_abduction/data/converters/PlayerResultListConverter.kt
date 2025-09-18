package com.example.alien_abduction.data.converters

import androidx.room.TypeConverter
import com.example.alien_abduction.domain.dataModels.PlayerResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PlayerResultListConverter {
    @TypeConverter
    fun fromPlayerResultList(value: List<PlayerResult>): String = Json.encodeToString(value)

    @TypeConverter
    fun toPlayerResultList(value: String): List<PlayerResult> = Json.decodeFromString(value)
}