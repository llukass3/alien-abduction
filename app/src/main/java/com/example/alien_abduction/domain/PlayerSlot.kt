package com.example.alien_abduction.domain

import androidx.compose.ui.graphics.Color


enum class PlayerSlot(val color: Color) {
    PLAYER_1(Color.hsv(240f, 1f, 1f)),
    PLAYER_2(Color.hsv(0f, 1f, 1f)),
    PLAYER_3(Color.hsv(120f, 1f, 1f)),
    PLAYER_4(Color.hsv(55f, 1f, 1f)),
}