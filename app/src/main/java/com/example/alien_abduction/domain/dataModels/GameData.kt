package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

@Serializable
data class GameData(
    val locationLatitude: Double,
    val locationLongitude: Double,
    val playerGuesses: List<PlayerGuess>
)