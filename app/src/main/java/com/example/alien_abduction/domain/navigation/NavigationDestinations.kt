package com.example.alien_abduction.domain.navigation

import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.PlayerGuess
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class GameSetup(val gameMode: GameMode)

@Serializable
object ProfileScreen

@Serializable
object GameHistoryScreen

@Serializable
object AchievementsScreen

@Serializable
object AddNewLocationScreen

@Serializable
data class MainGameScreen(val gameConfigJson: String)

@Serializable
data class ResultScreen(val gameDataJson: String)