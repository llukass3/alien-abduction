package com.example.alien_abduction.presentation.viewModels

import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.GameMode

val demoGameConfig = GameConfiguration(
    mode = GameMode.CLASSIC,
    initialLatitude = 50.8284,
    initialLongitude = 7.1216,
    numberOfPlayers = 1,
    numberOfRounds = 1,
    countdown = 60f
)

val demoGameConfigRandomLocation = GameConfiguration(
    mode = GameMode.CLASSIC,
    initialLatitude = null,
    initialLongitude = null,
    numberOfPlayers = 1,
    numberOfRounds = 1,
    countdown = 60f
)