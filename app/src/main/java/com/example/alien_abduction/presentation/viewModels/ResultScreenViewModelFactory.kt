package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.dataModels.PlayerGuess

class ResultScreenViewModelFactory(
    private val gameData: GameData
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultScreenViewModel::class.java)) {
            return ResultScreenViewModel(gameData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}