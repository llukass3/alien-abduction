package com.example.alien_abduction.domain.repositories

import com.example.alien_abduction.domain.dataModels.GameHistoryEntry

//this repository describes the access to the game history from the database
interface GameHistoryRepository {
    suspend fun saveGameHistory(entry: GameHistoryEntry)
    suspend fun getAllGameHistory(): List<GameHistoryEntry>
}