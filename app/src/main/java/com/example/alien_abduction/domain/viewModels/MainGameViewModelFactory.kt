package com.example.alien_abduction.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.GameConfiguration
import com.example.alien_abduction.domain.GameMode

class MainGameViewModelFactory(
    private val gameConfiguration: GameConfiguration
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainGameViewModel::class.java)) {
            return MainGameViewModel(gameConfiguration) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}