package com.example.alien_abduction.domain.useCases

import com.example.alien_abduction.domain.repositories.StreetViewLocationsRepository
import com.example.alien_abduction.domain.dataModels.StreetViewLocation

class GetStreetViewLocationsUseCase(private val repository: StreetViewLocationsRepository) {
    suspend operator fun invoke(): List<StreetViewLocation> = repository.getData()
}