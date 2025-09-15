package com.example.alien_abduction.domain.util

import androidx.annotation.DrawableRes
import com.example.alien_abduction.R
import com.example.alien_abduction.domain.PlayerSlot

@DrawableRes
fun getPlayerMarker(player: PlayerSlot): Int {
    return when (player) {
        PlayerSlot.PLAYER_1 -> R.drawable.marker_blue
        PlayerSlot.PLAYER_2 -> R.drawable.marker_red
        PlayerSlot.PLAYER_3 -> R.drawable.marker_green
        PlayerSlot.PLAYER_4 -> R.drawable.marker_yellow
    }
}