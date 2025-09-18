package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

//Helferklasse für das Dekodieren des JSON Strings
@Serializable
data class StreetViewLocationsWrapper(
    val streetViewLocations: List<StreetViewLocation>
)
