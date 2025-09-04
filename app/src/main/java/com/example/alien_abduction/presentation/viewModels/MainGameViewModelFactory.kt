package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.useCases.GetStreetViewLocationsUseCase

class MainGameViewModelFactory(
    private val gameConfiguration: GameConfiguration,
    private val getStreetViewLocationsUseCase: GetStreetViewLocationsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainGameViewModel::class.java)) {
            return MainGameViewModel(gameConfiguration, getStreetViewLocationsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}