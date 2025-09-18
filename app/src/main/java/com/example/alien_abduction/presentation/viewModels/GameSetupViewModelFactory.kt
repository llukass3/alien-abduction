package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.repositories.CustomLocationRepository

class GameSetupViewModelFactory(
    private val gameMode: GameMode,
    private val customLocationRepo: CustomLocationRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSetupViewModel::class.java)) {
            return GameSetupViewModel(
                gameMode,
                customLocationRepo
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}