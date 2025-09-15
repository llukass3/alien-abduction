package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.GameModeData
import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.CustomScenario
import com.example.alien_abduction.domain.dataModels.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameSetupViewModel(val gameMode: GameMode): ViewModel() {

    val gameModeData = GameModeData.fromMode(gameMode)

    private val _players = MutableStateFlow(
        listOf(Player(PlayerSlot.PLAYER_1, "Lukas"))
    )
    val players = _players.asStateFlow()

    private val _numberOfRounds = MutableStateFlow(1)
    val numberOfRounds = _numberOfRounds.asStateFlow()

    private val _countdown = MutableStateFlow<Float?>(null)
    val countdown = _countdown.asStateFlow()

    private val _initialLatitude = MutableStateFlow<Double?>(null)
    val initialLatitude = _initialLatitude.asStateFlow()

    private val _initialLongitude = MutableStateFlow<Double?>(null)
    val initialLongitude = _initialLongitude.asStateFlow()

    private val _customScenarios = MutableStateFlow(listOf<CustomScenario>())
    val customScenarios = _customScenarios.asStateFlow()


    fun addPlayer(player: Player) {
        _players.value += player
    }

    fun removePlayer(player: Player) {
        _players.value -= player
    }

    fun setNumberOfRounds(value: Int) {
        if (value in 1..10)
            _numberOfRounds.value = value
    }

    fun setCountdown(value: Float) {
        if (value in 1f..300f)
            _countdown.value = value
    }

    fun buildGameConfiguration(): GameConfiguration {
        return GameConfiguration(
            mode = gameMode,
            initialLatitude = null,
            initialLongitude = null,
            players = players.value,
            numberOfRounds = numberOfRounds.value,
            countdown = countdown.value,
        )
    }
}