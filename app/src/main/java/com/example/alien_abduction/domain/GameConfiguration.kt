package com.example.alien_abduction.domain

import kotlinx.serialization.Serializable

@Serializable
data class GameConfiguration(
    val mode: GameMode,
    val initialLatitude: Double?,
    val initialLongitude: Double?,
    val numberOfPlayers: Int,
    val numberOfRounds: Int,
    val countdown: Float?
)