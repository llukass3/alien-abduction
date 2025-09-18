package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import kotlinx.serialization.Serializable

@Serializable
data class GameData(
    val gameMode: GameMode,
    val locationLatitude: Double,
    val locationLongitude: Double,
    val playerGuesses: List<PlayerGuess>
)