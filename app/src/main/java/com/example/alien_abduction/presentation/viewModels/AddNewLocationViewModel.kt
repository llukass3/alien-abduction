package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.alien_abduction.domain.dataModels.CustomLocation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AddNewLocationViewModel: ViewModel() {

    private val _currentLocationName = MutableStateFlow("Mein Szenario")
    val currentLocationName = _currentLocationName.asStateFlow()

    private val _currentLocationSelection = MutableStateFlow<LatLng?>(null)
    val currentLocationSelection = _currentLocationSelection.asStateFlow()

    fun setCurrentLocation(location: LatLng) {
        _currentLocationSelection.value = location
    }


    fun saveCurrentCustomLocation() {
        if(currentLocationSelection.value != null) {
            val newCustomLat = currentLocationSelection.value!!.latitude
            val newCustomLng = currentLocationSelection.value!!.longitude
            val newCustomLocation =
                CustomLocation(
                    name = currentLocationName.value,
                    latitude = newCustomLat,
                    longitude = newCustomLng
                )
        }
    }
}