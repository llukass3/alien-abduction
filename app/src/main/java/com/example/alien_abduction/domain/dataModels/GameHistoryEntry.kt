package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import kotlinx.serialization.Serializable

interface GameHistoryEntry

@Serializable
data class DefaultGameHistoryEntry(
    val gameMode: GameMode,
    val date: String,
    val timeOfDay: String,
    val playerResult: PlayerResult
): GameHistoryEntry

@Serializable
data class MultiplayerGameHistoryEntry(
    val gameMode: GameMode,
    val date: String,
    val timeOfDay: String,
    val playerResults: List<PlayerResult>
): GameHistoryEntry
