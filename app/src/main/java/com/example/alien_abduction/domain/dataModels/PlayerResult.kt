package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

//Diese Datenklasse repräsentiert das Ergebnis eines Tipps eines Spielers
@Serializable
data class PlayerResult(
    val playerGuess: PlayerGuess,
    val proximity: Double,
    val points: Int
)

