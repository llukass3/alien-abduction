package com.example.alien_abduction.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_locations")
data class CustomLocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val latitude: Double,
    val longitude: Double
)