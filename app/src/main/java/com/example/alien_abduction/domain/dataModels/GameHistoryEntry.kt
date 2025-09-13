package com.example.alien_abduction.domain.dataModels

import com.example.alien_abduction.domain.GameMode
import com.google.android.gms.maps.model.LatLng

data class GameHistoryEntry(
    val gameMode: GameMode,
    val date: String,
    val timeOfDay: String,
    val playTime: String,
    val proximity: String,
    val location: LatLng
)
