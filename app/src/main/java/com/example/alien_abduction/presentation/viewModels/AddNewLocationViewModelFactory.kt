package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.repositories.CustomLocationRepository

class AddNewLocationViewModelFactory(
    private val customLocationRepo: CustomLocationRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNewLocationViewModel::class.java)) {
            return AddNewLocationViewModel(
                customLocationRepo = customLocationRepo
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}