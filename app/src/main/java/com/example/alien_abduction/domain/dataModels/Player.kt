package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.PlayerSlot
import kotlinx.serialization.Serializable

//Diese Datenklasse repräsentiert einen Spieler
@Serializable
data class Player(
    val slot: PlayerSlot,
    val nickname: String
)
