package com.example.alien_abduction

import com.example.alien_abduction.gameLogic.GameModes
import kotlinx.serialization.Serializable

public interface NavigationDestination

@Serializable
object HomeScreen: NavigationDestination

@Serializable
data class GameSetup(val gameMode: GameModes): NavigationDestination