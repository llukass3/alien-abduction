package com.example.alien_abduction.domain.navigation

import com.example.alien_abduction.domain.GameMode
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
data class MainGameScreen(val gameConfigJson: String)

@Serializable
object ResultScreen