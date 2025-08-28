package com.example.alien_abduction.presentation.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.Status
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.compose.streetview.rememberStreetViewCameraPositionState
import com.google.maps.android.ktx.MapsExperimentalFeature
import kotlinx.coroutines.launch
import com.google.maps.android.StreetViewUtils.Companion.fetchStreetViewData
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.example.alien_abduction.BuildConfig

private const val TAG = "StreetViewScreen"
val invalidLocation = LatLng(32.429634, -96.828891)

@OptIn(MapsExperimentalFeature::class)
@Composable
fun StreetViewScreen(
    modifier: Modifier = Modifier
) {
    val singapore = LatLng(1.35, 103.87)
    var streetViewResult by remember { mutableStateOf(Status.NOT_FOUND) }

    val camera = rememberStreetViewCameraPositionState()
    LaunchedEffect(camera) {
        launch {
            snapshotFlow { camera.panoramaCamera }
                .collect {

                }
        }
        launch {
            snapshotFlow { camera.location }
                .collect {

                }
        }
        launch {
            streetViewResult =
                fetchStreetViewData(singapore, BuildConfig.MAPS_API_KEY)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.BottomStart,
    ) {
        if (streetViewResult == Status.OK) {
            StreetView(
                Modifier.matchParentSize(),
                cameraPositionState = camera,
                streetViewPanoramaOptionsFactory = {
                    StreetViewPanoramaOptions()
                        .position(singapore)
                },
            )
        } else {
            //executes if location offers no StreetView data
            Text("Location not available.")
        }
    }
}

@Preview
@Composable
fun StreetViewScreenPreview() {

}