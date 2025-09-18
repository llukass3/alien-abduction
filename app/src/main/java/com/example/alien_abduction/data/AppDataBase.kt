package com.example.alien_abduction.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.alien_abduction.data.converters.*

@Database(
    entities = [
        GameHistoryEntryEntity::class
               ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    GameModeConverter::class,
    PlayerSlotConverter::class,
    PlayerGuessConverter::class,
    PlayerResultConverter::class,
    PlayerResultListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameHistoryDao(): GameHistoryDao
}