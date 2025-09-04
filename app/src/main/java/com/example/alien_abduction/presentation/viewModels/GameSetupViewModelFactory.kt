package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.GameMode


class GameSetupViewModelFactory(
    private val gameMode: GameMode
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSetupViewModel::class.java)) {
            return GameSetupViewModel(gameMode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}