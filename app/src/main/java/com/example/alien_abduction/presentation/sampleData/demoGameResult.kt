package com.example.alien_abduction.presentation.sampleData

import com.example.alien_abduction.domain.Player
import com.example.alien_abduction.domain.dataModels.PlayerResult

val demoPlayerResult = PlayerResult(
    player = Player.PLAYER_1,
    playerName = "Lukas",
    proximity = 2.0,
    time = 100f,
    points = 3000
)

val demoPlayerResultLists = listOf(
    PlayerResult(
        player = Player.PLAYER_1,
        playerName = "Lukas",
        proximity = 2.0,
        time = 100f,
        points = 3000
    ),
    PlayerResult(
        player = Player.PLAYER_2,
        playerName = "Beyoncé",
        proximity = 2.0,
        time = 100f,
        points = 5000
    ),
    PlayerResult(
        player = Player.PLAYER_3,
        playerName = "Kelly",
        proximity = 2.0,
        time = 100f,
        points = 4500
    ),
    PlayerResult(
        player = Player.PLAYER_4,
        playerName = "Michelle",
        proximity = 2.0,
        time = 100f,
        points = 4200
    ),
)