package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

@Serializable
data class StreetViewLocationsWrapper(
    val streetViewLocations: List<StreetViewLocation>
)
