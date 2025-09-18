package com.example.alien_abduction.domain.dataModels

import kotlinx.serialization.Serializable

//Diese Datenklasse repräsentiert eine Location aus dem JSON Datensatz
@Serializable
data class StreetViewLocation(
    val id: String,
    val latitude: Double,
    val longitude: Double
)
