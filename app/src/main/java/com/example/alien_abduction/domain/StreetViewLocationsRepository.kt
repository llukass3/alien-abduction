package com.example.alien_abduction.domain

import com.example.alien_abduction.domain.dataModels.StreetViewLocation

interface StreetViewLocationsRepository {
    suspend fun getData(): List<StreetViewLocation>
}