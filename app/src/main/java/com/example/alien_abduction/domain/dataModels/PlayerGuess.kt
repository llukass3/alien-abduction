package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.PlayerSlot
import kotlinx.serialization.Serializable

@Serializable
data class PlayerGuess(
    val playerSlot: PlayerSlot,
    val playerName: String,
    val latitude: Double,
    val longitude: Double,
    val time: Float?,
)
