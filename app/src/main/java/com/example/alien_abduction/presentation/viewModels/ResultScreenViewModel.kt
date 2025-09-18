package com.example.alien_abduction.presentation.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.google.maps.android.SphericalUtil
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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

        val gameHistoryEntry = buildGameHistoryEntry()
    }

    private fun measureDistance(
        location: LatLng,
        guess: LatLng
    ): Double {
        return SphericalUtil.computeDistanceBetween(location, guess)
    }

    private fun calculateScore(distance: Double): Int {
        val maxScore = 5000
        val maxDistance = 4_000_000.0 // 4000 km
        val steepness = maxDistance / 6 // tweak 6 for balance; lower = steeper

        // Exponential decay
        val score = (maxScore * Math.exp(-distance / steepness)).toInt()
        return score.coerceIn(0, maxScore)
    }

    private fun buildPlayerResult(
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
    
    @RequiresApi(Build.VERSION_CODES.O)
    fun buildGameHistoryEntry(): GameHistoryEntry {
        return if (gameData.gameMode == GameMode.MULTIPLAYER)
            MultiplayerGameHistoryEntry(
                gameMode = gameData.gameMode,
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                timeOfDay = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
                playerResults = playerResults.value
            )
        else return DefaultGameHistoryEntry(
            gameMode = gameData.gameMode,
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            timeOfDay = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
            playerResult = playerResults.value.first()
        )
        
    }

}