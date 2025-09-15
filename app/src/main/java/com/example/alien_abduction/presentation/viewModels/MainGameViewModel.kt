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
import com.example.alien_abduction.domain.useCases.SelectRandomLocationUseCase
import com.example.alien_abduction.domain.useCases.TimerUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MainGameViewModel(
    private val gameConfiguration: GameConfiguration,
    private val getStreetViewLocationsUseCase: GetStreetViewLocationsUseCase,
    private val selectRandomLocationUseCase: SelectRandomLocationUseCase,
    //private val timerUseCase: TimerUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MainGameViewModel"
    }

    //The status of the current location. OK if street view data is available, NOT_FOUND otherwise
    private var _streetViewStatus = MutableStateFlow(Status.NOT_FOUND)
    val streetViewStatus = _streetViewStatus.asStateFlow()

    //collection of locations with available street view data
    private val _locations = MutableStateFlow<List<StreetViewLocation>>(emptyList())
    val locations = _locations.asStateFlow()

    //collection of locations already used in this game to avoid repeating locations
    private var _usedLocationIds = MutableStateFlow<MutableList<String>>(mutableListOf())

    //the initial location of the street view panorama
    private val _initialLocation = MutableStateFlow<LatLng?>(null)
    val initialLocation = _initialLocation.asStateFlow()

    private val _currentRound = MutableStateFlow(1)
    val currentRound = _currentRound.asStateFlow()

    val maxRounds = gameConfiguration.numberOfRounds

    //the time left in the game. Null if the game has no time limit
    private val _timeLeft = MutableStateFlow(gameConfiguration.countdown)
    val timeLeft = _timeLeft.asStateFlow()

    private val _timerFinished = MutableStateFlow(false)
    val timerFinished = _timerFinished.asStateFlow()

    //the currently guessed location on the google map
    private val _currentGuess = MutableStateFlow<LatLng?>(null)
    val currentGuess = _currentGuess.asStateFlow()

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
                startTimer(
                    gameConfiguration.countdown,
                    onTimerFinished = {
                        _timerFinished.value = true
                    }
                )
            }
        }
    }

   private fun loadStreetViewLocations() {
        viewModelScope.launch {
            val streetViewLocations = getStreetViewLocationsUseCase()
            _locations.value = streetViewLocations
        }
   }

    private var timerJob: Job? = null
    private fun startTimer(
        initialTime: Float?,
        onTimerFinished: () -> Unit = {}
    ) {
        if (initialTime == null) return
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var timeLeftSeconds = initialTime
            while (timeLeftSeconds > 0f) {
                delay(1000L)
                timeLeftSeconds -= 1f
                _timeLeft.value = timeLeftSeconds.coerceAtLeast(0f)
                if (timeLeftSeconds <= 0f) {
                    _timeLeft.value = 0f
                    onTimerFinished()
                    break
                }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun resetTimer() {
        stopTimer()
        val initialTime = gameConfiguration.countdown
        if (initialTime != null) {
            _timeLeft.value = initialTime
            startTimer(initialTime)
        }
    }

    fun setCurrentGuess(latLng: LatLng) {
        _currentGuess.value = latLng
    }

    /*fun generateGameResult(): GameResult {

    }*/

}