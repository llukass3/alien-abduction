package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.repositories.GameHistoryRepository

class GameHistoryViewModelFactory(
    private val gameHistoryRepo: GameHistoryRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameHistoryViewModel::class.java)) {
            return GameHistoryViewModel(
                gameHistoryRepo
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}