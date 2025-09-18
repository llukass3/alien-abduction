package com.example.alien_abduction.presentation.sampleData

import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.dataModels.PlayerGuess

val demoPlayerGuess = PlayerGuess(
    playerSlot = PlayerSlot.PLAYER_1,
    playerName = "Lukas",
    latitude = 47.5576,
    longitude = 7.5883,
    timeLeft = 100f,
)

val demoPlayerGuessLists = listOf(
    PlayerGuess(
        playerSlot = PlayerSlot.PLAYER_1,
        playerName = "Lukas",
        latitude = 47.5576,
        longitude = 7.5883,
        timeLeft = 100f,
    ),
    PlayerGuess(
        playerSlot = PlayerSlot.PLAYER_2,
        playerName = "Beyoncé",
        latitude = 46.5576,
        longitude = 8.5883,
        timeLeft = 100f,
    ),
    PlayerGuess(
        playerSlot = PlayerSlot.PLAYER_3,
        playerName = "Kelly",
        latitude = 45.5576,
        longitude = 9.5883,
        timeLeft = 100f,
    ),
    PlayerGuess(
        playerSlot = PlayerSlot.PLAYER_4,
        playerName = "Michelle",
        latitude = 43.5576,
        longitude = 10.5883,
        timeLeft = 100f,
    ),
)

val demoGameData = GameData(
    gameMode = GameMode.CLASSIC,
    locationLatitude = 20.5576,
    locationLongitude = 4.5883,
    playerGuesses = listOf(demoPlayerGuess)
)