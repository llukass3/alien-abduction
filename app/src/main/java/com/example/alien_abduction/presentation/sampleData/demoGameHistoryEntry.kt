package com.example.alien_abduction.presentation.sampleData

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.GameHistoryEntry
import com.example.alien_abduction.domain.dataModels.PlayerResult
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
val demoDefaultGameHistoryEntry = GameHistoryEntry(
    gameMode = GameMode.CLASSIC,
    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
    timeOfDay = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
    playerResults =  listOf(
        PlayerResult(
            playerGuess = demoPlayerGuess,
            proximity = 1000.0,
            points = 1250
        )
    ),
)

@RequiresApi(Build.VERSION_CODES.O)
val demoMultiplayerGameHistoryEntry = GameHistoryEntry(
    gameMode = GameMode.MULTIPLAYER,
    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
    timeOfDay = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
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