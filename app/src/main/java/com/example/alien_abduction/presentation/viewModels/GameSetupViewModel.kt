package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.GameModeData
import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.CustomLocation
import com.example.alien_abduction.domain.dataModels.Player
import com.example.alien_abduction.presentation.sampleData.demoCustomLocation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameSetupViewModel(val gameMode: GameMode): ViewModel() {

    val gameModeData = GameModeData.fromMode(gameMode)

    private val _players = MutableStateFlow(
        listOf(Player(PlayerSlot.PLAYER_1, "Spieler 1"))
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

    private val _customLocations = MutableStateFlow(/*listOf<CustomLocation>()*/ demoCustomLocation)
    val customLocations = _customLocations.asStateFlow()

    private val _customLocationView = MutableStateFlow<LatLng?>(null)
    val customLocationView = _customLocationView.asStateFlow()

    init {
        when (gameMode) {
            GameMode.CLASSIC -> {_countdown.value = 180f}
            GameMode.EXPLORE -> {}
            GameMode.MULTIPLAYER -> {_countdown.value = 180f}
            GameMode.CHALLENGE -> {}
        }
    }

    fun addPlayer() {
        when (_players.value.size) {
            1 -> {
                _players.value += Player(PlayerSlot.PLAYER_2, "Spieler 2")
            }
            2 -> {
                _players.value += Player(PlayerSlot.PLAYER_3, "Spieler 3")
            }
            3 -> {
                _players.value += Player(PlayerSlot.PLAYER_4, "Spieler 4")
            }
        }
    }

    fun removePlayer() {
        if (_players.value.size > 1)
            _players.value -= _players.value.last()
    }

    fun changePlayerName(playerIndex: Int, newName: String) {
        val updatedPlayers = _players.value.toMutableList()
        val oldPlayer = updatedPlayers[playerIndex]
        updatedPlayers[playerIndex] = oldPlayer.copy(nickname = newName)
        _players.value = updatedPlayers
    }

    fun setCountdown(value: Float) {
        if (value in 1f..300f)
            _countdown.value = value
    }

    fun setInitialLocation(location: LatLng) {
        _initialLatitude.value = location.latitude
        _initialLongitude.value = location.longitude
    }

    fun addCustomLocation(name: String, location: LatLng) {
        _customLocations.value +=
            CustomLocation(
                name = name,
                latitude = location.latitude,
                longitude = location.longitude
            )
    }

    fun removeCustomLocation(customLocation: CustomLocation) {
        _customLocations.value -= customLocation
    }

    fun buildGameConfiguration(): GameConfiguration {
        return GameConfiguration(
            mode = gameMode,
            initialLatitude = _initialLatitude.value,
            initialLongitude = _initialLatitude.value,
            players = players.value,
            numberOfRounds = numberOfRounds.value,
            countdown = countdown.value,
        )
    }

}