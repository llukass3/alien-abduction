package com.example.alien_abduction.domain.repositories

import com.example.alien_abduction.domain.dataModels.StreetViewLocation

//this repository describes the access to the street view locations from the JSON file
interface StreetViewLocationsRepository {
    suspend fun getData(): List<StreetViewLocation>
}