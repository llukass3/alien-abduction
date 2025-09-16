package com.example.alien_abduction.domain.util

import com.google.android.gms.maps.model.LatLng
import java.util.Locale

fun formatLatLngDMS(latLng: LatLng): String {
    fun toDMS(coordinate: Double, isLatitude: Boolean): String {
        val absolute = Math.abs(coordinate)
        val degrees = absolute.toInt()
        val minutesDecimal = (absolute - degrees) * 60
        val minutes = minutesDecimal.toInt()
        val seconds = (minutesDecimal - minutes) * 60

        val direction = when {
            isLatitude && coordinate >= 0 -> "N"
            isLatitude && coordinate < 0 -> "S"
            !isLatitude && coordinate >= 0 -> "E"
            else -> "W"
        }

        return String.format(
            Locale.US,
            "%02d°%02d'%04.1f\"%s",
            degrees, minutes, seconds, direction
        )
    }

    val latDMS = toDMS(latLng.latitude, true)
    val lngDMS = toDMS(latLng.longitude, false)
    return "$latDMS $lngDMS"
}