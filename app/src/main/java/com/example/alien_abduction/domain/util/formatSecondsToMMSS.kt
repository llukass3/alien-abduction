package com.example.alien_abduction.domain.util

import java.util.Locale

fun formatSecondsToMMSS(seconds: Float): String {
    val totalSeconds = seconds.toInt()
    val minutes = totalSeconds / 60
    val secs = totalSeconds % 60
    return String.format(Locale.US, "%02d:%02d", minutes, secs)
}