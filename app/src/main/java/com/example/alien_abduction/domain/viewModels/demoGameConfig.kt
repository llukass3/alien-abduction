package com.example.alien_abduction.domain.viewModels

import com.example.alien_abduction.domain.GameConfiguration
import com.example.alien_abduction.domain.GameMode

val demoGameConfig = GameConfiguration(
    mode = GameMode.CLASSIC,
    initialLatitude = 1.35,
    initialLongitude = 103.87,
    numberOfPlayers = 1,
    numberOfRounds = 1,
    countdown = 60f
)