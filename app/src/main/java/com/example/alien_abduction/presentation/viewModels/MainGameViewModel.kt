package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.dataModels.StreetViewLocation
import com.example.alien_abduction.domain.useCases.GetStreetViewLocationsUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.google.maps.android.StreetViewUtils.Companion.fetchStreetViewData
import com.example.alien_abduction.BuildConfig
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.dataModels.PlayerGuess
import com.example.alien_abduction.domain.useCases.SelectRandomLocationUseCase
import com.example.alien_abduction.domain.useCases.TimerUseCase

class MainGameViewModel(
    val gameConfiguration: GameConfiguration,
    private val getStreetViewLocationsUseCase: GetStreetViewLocationsUseCase,
    private val selectRandomLocationUseCase: SelectRandomLocationUseCase,
    private val timerUseCase: TimerUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MainGameViewModel"
    }

/*--------------------------Locations-------------------------------------------------------------*/

    //collection of locations with available street view data
    private val _locations = MutableStateFlow<List<StreetViewLocation>>(emptyList())
    val locations = _locations.asStateFlow()

    //collection of locations already used in this game to avoid repeating locations
    private var _usedLocationIds = MutableStateFlow<MutableList<String>>(mutableListOf())

    //the initial location of the street view panorama
    private val _initialLocation = MutableStateFlow<LatLng?>(null)
    val initialLocation = _initialLocation.asStateFlow()

    //The status of the current location. OK if street view data is available, NOT_FOUND otherwise
    private var _streetViewStatus = MutableStateFlow(Status.NOT_FOUND)
    val streetViewStatus = _streetViewStatus.asStateFlow()

    //the currently guessed location on the google map
    private val _currentGuess = MutableStateFlow<LatLng?>(null)
    val currentGuess = _currentGuess.asStateFlow()

/*--------------------------Players---------------------------------------------------------------*/

    private val _currentPlayerIndex = MutableStateFlow(0)
    private val _currentPlayer = MutableStateFlow(
        gameConfiguration.players[_currentPlayerIndex.value]
    )
    val currentPlayer = _currentPlayer.asStateFlow()

    private val _playerGuesses = MutableStateFlow(emptyList<PlayerGuess>())


/*--------------------------Rounds----------------------------------------------------------------*/

    private val _currentRound = MutableStateFlow(1)
    val currentRound = _currentRound.asStateFlow()

    val maxRounds = gameConfiguration.numberOfRounds


/*--------------------------Timer-----------------------------------------------------------------*/

    //the time left in the game. Null if the game has no time limit
    private val _timeLeft = MutableStateFlow(gameConfiguration.countdown)
    val timeLeft = _timeLeft.asStateFlow()

    private val _timerFinished = MutableStateFlow(false)
    val timerFinished = _timerFinished.asStateFlow()

/*--------------------------UI--------------------------------------------------------------------*/

    private var _isMapOpened = MutableStateFlow(false)
    val isMapOpened = _isMapOpened.asStateFlow()

/*------------------------------------------------------------------------------------------------*/

    init {
        viewModelScope.launch {
            _locations.value = getStreetViewLocationsUseCase()
            if (gameConfiguration.initialLatitude != null && gameConfiguration.initialLongitude != null) {
                val candidate = LatLng(
                    gameConfiguration.initialLatitude,
                    gameConfiguration.initialLongitude
                )
                _streetViewStatus.value = fetchStreetViewData(candidate, BuildConfig.MAPS_API_KEY)
                if (_streetViewStatus.value == Status.OK)
                    _initialLocation.value = candidate
            }
            else {
                _initialLocation.value = selectRandomLocationUseCase(
                    streetViewLocations = _locations.value,
                    usedLocationIds = _usedLocationIds.value,
                    updateValues = { status, usedId ->
                        _streetViewStatus.value = status
                        if (usedId != null)
                            _usedLocationIds.value.add(usedId)
                    }
                )
            }

            if (gameConfiguration.countdown != null) {
                startTimer()
            }
        }
    }

    fun setMapState(isOpen: Boolean) {
        _isMapOpened.value = isOpen
    }

    fun startTimer() {
        gameConfiguration.countdown?.let { initialTime ->
            timerUseCase.startTimer(
                initialTime,
                viewModelScope,
                onTick = { _timeLeft.value = it },
                onTimerFinished = { _timerFinished.value = true }
            )
        }
    }

    fun stopTimer() = timerUseCase.stopTimer()

    fun resetTimer() {
        gameConfiguration.countdown?.let { initialTime ->
            timerUseCase.resetTimer(
                initialTime,
                viewModelScope,
                onTick = { _timeLeft.value = it },
                onTimerFinished = { _timerFinished.value = true }
            )
        }
    }

   private fun loadStreetViewLocations() {
        viewModelScope.launch {
            val streetViewLocations = getStreetViewLocationsUseCase()
            _locations.value = streetViewLocations
        }
   }

    fun setCurrentGuess(latLng: LatLng) {
        _currentGuess.value = latLng
    }

    fun hasNextPlayer(): Boolean {
        return _currentPlayerIndex.value < gameConfiguration.players.size -1
    }

    /** moves to the next player*/
    fun nextPlayer(){
        setMapState(false)
        resetTimer()
        _currentGuess.value = null //reset current guess
        _currentPlayerIndex.value++ //move to next player index
        _currentPlayer.value = gameConfiguration.players[_currentPlayerIndex.value] //update current player
    }

    /**saves the current player guess to _playerGuesses list*/
    fun saveCurrentGuess() {
        if (_currentGuess.value != null) {
            _playerGuesses.value += PlayerGuess(
                playerSlot = currentPlayer.value.slot,
                playerName = currentPlayer.value.nickname,
                latitude = currentGuess.value!!.latitude,
                longitude = currentGuess.value!!.longitude,
                timeLeft = timeLeft.value
            )
        }
    }

    fun buildGameData(): GameData? {
        val initialLocation = initialLocation.value
        val guessedLocation = currentGuess.value

        if(initialLocation != null && guessedLocation != null) {
            return GameData(
                locationLatitude = initialLocation.latitude,
                locationLongitude = initialLocation.longitude,
                playerGuesses = _playerGuesses.value
            )
        }
        return null
    }

}