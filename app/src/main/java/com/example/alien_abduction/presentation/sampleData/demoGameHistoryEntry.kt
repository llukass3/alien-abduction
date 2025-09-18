package com.example.alien_abduction.presentation.sampleData

import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.DefaultGameHistoryEntry
import com.example.alien_abduction.domain.dataModels.MultiplayerGameHistoryEntry
import com.example.alien_abduction.domain.dataModels.PlayerResult


val demoDefaultGameHistoryEntry = DefaultGameHistoryEntry(
    gameMode = GameMode.CLASSIC,
    date = "17.09.2025",
    timeOfDay = "23:12",
    playerResult = PlayerResult(
        playerGuess = demoPlayerGuess,
        proximity = 1000.0,
        points = 1250
    ),
)

val demoMultiplayerGameHistoryEntry = MultiplayerGameHistoryEntry(
    gameMode = GameMode.MULTIPLAYER,
    date = "17.09.2025",
    timeOfDay = "23:12",
    playerResults = listOf(
        PlayerResult(
            playerGuess = demoPlayerGuessLists[0],
            proximity = 1000.0,
            points = 992
        ),
        PlayerResult(
            playerGuess = demoPlayerGuessLists[1],
            proximity = 2120.0,
            points = 9102
        ),
        PlayerResult(
            playerGuess = demoPlayerGuessLists[2],
            proximity = 2740.0,
            points = 2310
        ),
        PlayerResult(
            playerGuess = demoPlayerGuessLists[3],
            proximity = 3400.0,
            points = 4215
        )
    ),
)