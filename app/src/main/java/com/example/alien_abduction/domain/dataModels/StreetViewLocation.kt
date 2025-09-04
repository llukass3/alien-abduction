package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

@Serializable
data class StreetViewLocation(
    val id: String,
    val latitude: Double,
    val longitude: Double
)
