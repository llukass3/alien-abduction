package com.example.alien_abduction.data.repositoryImplementations

import com.example.alien_abduction.data.GameHistoryDao
import com.example.alien_abduction.data.GameHistoryEntryEntity
import com.example.alien_abduction.domain.dataModels.GameHistoryEntry
import com.example.alien_abduction.domain.repositories.GameHistoryRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GameHistoryRepositoryImpl(
    private val dao: GameHistoryDao
) : GameHistoryRepository {

    override suspend fun saveGameHistory(entry: GameHistoryEntry) {
        val entity = GameHistoryEntryEntity(
            // map domain to entity, using your converters or manual mapping
            gameMode = entry.gameMode.name,
            date = entry.date,
            timeOfDay = entry.timeOfDay,
            playerResultsJson = Json.encodeToString(entry.playerResults)
        )
        dao.insert(entity)
    }

    override suspend fun getAllGameHistory(): List<GameHistoryEntry> {
        return dao.getAll().map { entity ->
            GameHistoryEntry(
                gameMode = com.example.alien_abduction.domain.GameMode.valueOf(entity.gameMode),
                date = entity.date,
                timeOfDay = entity.timeOfDay,
                playerResults = Json.decodeFromString(entity.playerResultsJson)
            )
        }
    }
}