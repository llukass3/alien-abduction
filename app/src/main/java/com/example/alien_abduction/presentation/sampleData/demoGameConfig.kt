package com.example.alien_abduction.presentation.sampleData

import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.Player

val demoGameConfig = GameConfiguration(
    mode = GameMode.CLASSIC,
    initialLatitude = 50.8284,
    initialLongitude = 7.1216,
    players = listOf(Player(PlayerSlot.PLAYER_1, "Lukas")),
    numberOfRounds = 1,
    countdown = 60f
)

val demoGameConfigRandomLocation = GameConfiguration(
    mode = GameMode.CLASSIC,
    initialLatitude = null,
    initialLongitude = null,
    players = listOf(Player(PlayerSlot.PLAYER_1, "Lukas")),
    numberOfRounds = 1,
    countdown = 10f
)