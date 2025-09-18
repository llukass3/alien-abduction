package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.PlayerSlot
import kotlinx.serialization.Serializable

//Diese Datenklasse repräsentiert einen Standort-Tipp eines Spielers und zeigt an, wie viel Zeit noch übrig war
@Serializable
data class PlayerGuess(
    val playerSlot: PlayerSlot,
    val playerName: String,
    val latitude: Double,
    val longitude: Double,
    val timeLeft: Float?,
)
