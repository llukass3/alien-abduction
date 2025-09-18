package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.DefaultGameHistoryEntry
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.dataModels.GameHistoryEntry
import com.example.alien_abduction.domain.dataModels.MultiplayerGameHistoryEntry
import com.google.android.gms.maps.model.LatLng
import com.example.alien_abduction.domain.dataModels.PlayerGuess
import com.example.alien_abduction.domain.dataModels.PlayerResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ResultScreenViewModel(val gameData: GameData) : ViewModel() {

    val alienLocation = LatLng(gameData.locationLatitude, gameData.locationLongitude)

    private val _playerResults = MutableStateFlow<List<PlayerResult>>(emptyList())
    val playerResults = _playerResults.asStateFlow()

    init {
        val playerResultsUnsorted = gameData.playerGuesses.map {
            buildPlayerResult(
                location = LatLng(gameData.locationLatitude, gameData.locationLongitude),
                playerGuess = it
            )
        }

        _playerResults.value = playerResultsUnsorted.sortedByDescending { it.points }
    }

    //TODO: Implement
    fun measureDistance(
        location: LatLng,
        guess: LatLng
    ): Double {
        return 0.0
    }

    //TODO: Implement
    fun calculateScore(distance: Double): Int {
        return 1
    }

    fun buildPlayerResult(
        location: LatLng,
        playerGuess: PlayerGuess
    ): PlayerResult {
        val guessedLocation = LatLng(playerGuess.latitude, playerGuess.longitude)
        val proximity = measureDistance(location, guessedLocation)
        val score = calculateScore(proximity)

        return PlayerResult(
            playerGuess = playerGuess,
            proximity = proximity,
            points = score
        )
    }
    
    fun buildGameHistoryEntry(): GameHistoryEntry {
        if (gameData.gameMode == GameMode.MULTIPLAYER)
            return MultiplayerGameHistoryEntry(
                gameMode = gameData.gameMode,
                date = TODO(),
                timeOfDay = TODO(),
                playerResults = TODO()
            )
        else return DefaultGameHistoryEntry(
            gameMode = TODO(),
            date = TODO(),
            timeOfDay = TODO(),
            playerResult = TODO()
        ) 
        
    }

}