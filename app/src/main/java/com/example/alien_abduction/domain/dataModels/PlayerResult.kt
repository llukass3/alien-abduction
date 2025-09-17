package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

@Serializable
data class PlayerResult(
    val playerGuess: PlayerGuess,
    val proximity: Double,
    val points: Int
)

