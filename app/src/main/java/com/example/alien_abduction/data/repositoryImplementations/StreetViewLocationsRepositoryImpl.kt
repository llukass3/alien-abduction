package com.example.alien_abduction.data.repositoryImplementations

import android.content.Context
import com.example.alien_abduction.domain.dataModels.StreetViewLocation
import com.example.alien_abduction.domain.repositories.StreetViewLocationsRepository
import com.example.alien_abduction.domain.dataModels.StreetViewLocationsWrapper
import kotlinx.serialization.json.Json

class StreetViewLocationsRepositoryImpl(private val context: Context):
    StreetViewLocationsRepository {
    override suspend fun getData(): List<StreetViewLocation> {
        val jsonString = context.assets.open("street_view_locations.json").bufferedReader().use { it.readText() }
        val wrapper = Json.decodeFromString<StreetViewLocationsWrapper>(jsonString)
        return wrapper.streetViewLocations
    }
}