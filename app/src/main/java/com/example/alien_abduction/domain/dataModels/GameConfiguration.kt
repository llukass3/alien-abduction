package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import kotlinx.serialization.Serializable

/*Diese Datenklasse repräsentiert die Konfiguration eines Spiels. Sie wird im Game Setup
* erstellt und an das view Model des Hauptspiels übergeben, wodurch dieses das Spiel erstellen kann */
@Serializable
data class GameConfiguration(
    val mode: GameMode,
    val initialLatitude: Double?,
    val initialLongitude: Double?,
    val players: List<Player>,
    val numberOfRounds: Int,
    val countdown: Float?
)