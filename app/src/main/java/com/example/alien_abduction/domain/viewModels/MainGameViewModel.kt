package com.example.alien_abduction.domain.viewModels

import androidx.lifecycle.ViewModel
import com.example.alien_abduction.domain.GameConfiguration
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainGameViewModel (val gameConfiguration: GameConfiguration): ViewModel() {

    val initialLocation =
        if (gameConfiguration.initialLatitude != null && gameConfiguration.initialLongitude != null)
            LatLng(gameConfiguration.initialLatitude, gameConfiguration.initialLongitude)
        else
            generateRandomStreetViewPanorama()

    private val _timeLeft = MutableStateFlow(gameConfiguration.countdown)
    val timeLeft = _timeLeft.asStateFlow()

    private fun generateRandomStreetViewPanorama(): LatLng {
        return LatLng(0.0, 0.0)
    }
}