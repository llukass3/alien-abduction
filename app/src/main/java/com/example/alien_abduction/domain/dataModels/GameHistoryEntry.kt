package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import kotlinx.serialization.Serializable

//Diese Datenklasse repräsentiert einen Eintrag in der Liste des Spielverlaufs
@Serializable
data class GameHistoryEntry(
    val gameMode: GameMode,
    val date: String,
    val timeOfDay: String,
    val playerResults: List<PlayerResult>
)
