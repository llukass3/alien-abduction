package com.example.alien_abduction.data.converters

import androidx.room.TypeConverter
import com.example.alien_abduction.domain.PlayerSlot

class PlayerSlotConverter {
    @TypeConverter
    fun fromPlayerSlot(value: PlayerSlot): String = value.name

    @TypeConverter
    fun toPlayerSlot(value: String): PlayerSlot = PlayerSlot.valueOf(value)
}