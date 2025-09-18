package com.example.alien_abduction.domain.repositories

import com.example.alien_abduction.domain.dataModels.GameHistoryEntry

interface GameHistoryRepository {
    suspend fun saveGameHistory(entry: GameHistoryEntry)
    suspend fun getAllGameHistory(): List<GameHistoryEntry>
}