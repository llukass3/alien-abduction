package com.example.alien_abduction.domain

import androidx.compose.ui.graphics.Color


enum class PlayerSlot(val position: String, val color: Color) {
    PLAYER_1("Spieler 1", Color.hsv(240f, 1f, 1f)),
    PLAYER_2("Spieler 2", Color.hsv(0f, 1f, 1f)),
    PLAYER_3("Spieler 3", Color.hsv(120f, 1f, 1f)),
    PLAYER_4("Spieler 4", Color.hsv(55f, 1f, 1f)),
}