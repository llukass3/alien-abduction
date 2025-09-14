package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.dataModels.PlayerResult

class ResultScreenViewModelFactory(
    private val playerResult: PlayerResult
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainGameViewModel::class.java)) {
            return ResultScreenViewModel(playerResult) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}