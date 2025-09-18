package com.example.alien_abduction.domain.repositories

import com.example.alien_abduction.domain.dataModels.CustomLocation

interface CustomLocationRepository {
    suspend fun addLocation(location: CustomLocation)
    suspend fun removeLocation(location: CustomLocation)
    suspend fun getAllLocations(): List<CustomLocation>
}