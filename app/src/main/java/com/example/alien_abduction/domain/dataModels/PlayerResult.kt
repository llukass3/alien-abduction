package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.Player

data class PlayerResult(
    val player: Player,
    val playerName: String,
    val proximity: Double,
    val time: Float?,
    val points: Int
)
