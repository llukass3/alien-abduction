package com.example.alien_abduction.presentation.composables.screens.mainGameScreenComposables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.domain.dataModels.Player
import com.example.alien_abduction.domain.util.getPlayerMarker
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberUpdatedMarkerState


@Composable
fun MainGameMap(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    onMapClick: (LatLng) -> Unit = {},
    currentGuess: LatLng?,
    currentPlayer: Player
) {
    Box(
        modifier = modifier
    ) {
        GoogleMap(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 15.dp, vertical = 70.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .border(width = 3.5.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(25.dp)),
            cameraPositionState = cameraPositionState,
            mapColorScheme = ComposeMapColorScheme.DARK,
            uiSettings = MapUiSettings(mapToolbarEnabled = false),
            onMapClick = {onMapClick(it)}

        ) {
            currentGuess?.let {
                val markerState = rememberUpdatedMarkerState(position = it)
                AdvancedMarker(
                    state = markerState,
                    icon = BitmapDescriptorFactory.fromResource(getPlayerMarker(currentPlayer.slot))
                )
            }
        }
    }
}