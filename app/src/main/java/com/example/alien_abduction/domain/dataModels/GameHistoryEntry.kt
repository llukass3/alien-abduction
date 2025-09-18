package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import kotlinx.serialization.Serializable

@Serializable
data class GameHistoryEntry(
    val gameMode: GameMode,
    val date: String,
    val timeOfDay: String,
    val playerResults: List<PlayerResult>
)
