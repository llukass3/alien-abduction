package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alien_abduction.domain.dataModels.GameHistoryEntry
import com.example.alien_abduction.domain.repositories.GameHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameHistoryViewModel(
    private val gameHistoryRepo: GameHistoryRepository
): ViewModel() {

    private val _gameHistory = MutableStateFlow<List<GameHistoryEntry>>(emptyList())
    val gameHistory = _gameHistory.asStateFlow()

    init {
        fetchGameHistory()
    }

    private fun fetchGameHistory() {
        viewModelScope.launch {
            val entries = gameHistoryRepo.getAllGameHistory()
            _gameHistory.value = entries
        }
    }
}