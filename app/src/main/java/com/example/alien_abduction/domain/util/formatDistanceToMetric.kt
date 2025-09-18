package com.example.alien_abduction.domain.util

import java.util.Locale

fun formatDistanceToMetric(distance: Double): String {
    return if (distance < 1000) {
        "${distance.toInt()} m"
    } else {
        val inKilometers = distance / 1000
        // Use Locale.GERMANY for comma as decimal separator, or replace '.' with ',' manually
        String.format(Locale.GERMANY, "%.1f km", inKilometers)
    }
}