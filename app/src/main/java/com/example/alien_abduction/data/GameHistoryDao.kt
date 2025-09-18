package com.example.alien_abduction.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameHistoryDao {
    @Insert
    suspend fun insert(entry: GameHistoryEntryEntity)

    @Query("SELECT * FROM game_history")
    suspend fun getAll(): List<GameHistoryEntryEntity>
}