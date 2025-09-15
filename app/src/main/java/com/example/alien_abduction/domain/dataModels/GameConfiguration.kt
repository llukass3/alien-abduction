package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import kotlinx.serialization.Serializable

@Serializable
data class GameConfiguration(
    val mode: GameMode,
    val initialLatitude: Double?,
    val initialLongitude: Double?,
    val players: List<Player>,
    val numberOfRounds: Int,
    val countdown: Float?
)