package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alien_abduction.BuildConfig
import com.example.alien_abduction.domain.dataModels.CustomLocation
import com.example.alien_abduction.domain.repositories.CustomLocationRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.Status
import com.google.maps.android.StreetViewUtils.Companion.fetchStreetViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AddNewLocationViewModel(
    private val customLocationRepo: CustomLocationRepository
): ViewModel() {

    private val _currentLocationName = MutableStateFlow("Mein Szenario")
    val currentLocationName = _currentLocationName.asStateFlow()

    private val _currentLocationSelection = MutableStateFlow<LatLng?>(null)
    val currentLocationSelection = _currentLocationSelection.asStateFlow()

    private var _streetViewStatus = MutableStateFlow(Status.NOT_FOUND)
    val streetViewStatus = _streetViewStatus.asStateFlow()

    fun setCurrentLocation(location: LatLng) {
        _currentLocationSelection.value = location
    }

    fun setCurrentLocationName(name: String) {
        _currentLocationName.value = name
    }

    fun checkForStreetViewData() {
        if(currentLocationSelection.value != null) {
            val candidate = _currentLocationSelection.value!!
            viewModelScope.launch {
                _streetViewStatus.value = fetchStreetViewData(candidate, BuildConfig.MAPS_API_KEY)
            }
        }
    }


    fun saveCurrentCustomLocation() {
        val candidate = _currentLocationSelection.value
        if(candidate != null) {
            val newCustomLocation = CustomLocation(
                name = currentLocationName.value,
                latitude = candidate.latitude,
                longitude = candidate.longitude
            )
            viewModelScope.launch {
                customLocationRepo.addLocation(newCustomLocation)
            }
        }
    }
}