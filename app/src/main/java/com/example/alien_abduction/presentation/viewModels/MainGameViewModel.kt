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

class MainGameViewModel(
    private val gameConfiguration: GameConfiguration,
    private val getStreetViewLocationsUseCase: GetStreetViewLocationsUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MainGameViewModel"
    }

    private var _streetViewStatus = MutableStateFlow(Status.NOT_FOUND)
    val streetViewStatus = _streetViewStatus.asStateFlow()

    private val _locations = MutableStateFlow<List<StreetViewLocation>>(emptyList())
    val locations = _locations.asStateFlow()

    private val _initialLocation = MutableStateFlow<LatLng?>(null)
    val initialLocation = _initialLocation.asStateFlow()

    private var _usedLocationIds = MutableStateFlow<MutableList<String>>(mutableListOf())

    private val _timeLeft = MutableStateFlow(gameConfiguration.countdown)
    val timeLeft = _timeLeft.asStateFlow()

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
                selectRandomLocation()
            }
        }

    }

   private fun loadStreetViewLocations() {
        viewModelScope.launch {
            val streetViewLocations = getStreetViewLocationsUseCase()
            _locations.value = streetViewLocations
        }
    }

    /** searches for random unused location, checks streetView availability and assigns it as current location */
    private suspend fun selectRandomLocation() {
        while(true) {
            //select random location from list
            val randomIndex: Int = (0 until _locations.value.size).random()
            val candidate = _locations.value[randomIndex]

            //check if location has already been used
            if(!_usedLocationIds.value.contains(candidate.id)) {
                //add location to used locations
                _usedLocationIds.value.add(candidate.id)

                //convert candidate to LatLng format
                val candidateLatLng = LatLng(candidate.latitude, candidate.longitude)

                //check whether street view data is available for the location
                _streetViewStatus.value = fetchStreetViewData(candidateLatLng, BuildConfig.MAPS_API_KEY)
                if(_streetViewStatus.value == Status.OK) {
                    //assign candidate as current location
                    _initialLocation.value = LatLng(candidate.latitude, candidate.longitude)
                    break
                }
            }
        }
    }

}