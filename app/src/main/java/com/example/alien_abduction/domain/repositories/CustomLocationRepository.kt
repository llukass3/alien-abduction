package com.example.alien_abduction.domain.repositories

import com.example.alien_abduction.domain.dataModels.CustomLocation

//This repository describes the access to a custom location from the database
interface CustomLocationRepository {
    suspend fun addLocation(location: CustomLocation)
    suspend fun removeLocation(location: CustomLocation)
    suspend fun getAllLocations(): List<CustomLocation>
}