package com.example.alien_abduction.presentation.composables.screens.gameSetup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.R
import com.example.alien_abduction.presentation.composables.customComposables.MainGameButton
import com.example.alien_abduction.presentation.viewModels.AddNewLocationViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.rememberUpdatedMarkerState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun AddNewLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: AddNewLocationViewModel,
    onBackToGameSetup: () -> Unit = {}
) {

    val currentLocationSelection by viewModel.currentLocationSelection.collectAsState()

    Box(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .matchParentSize()
                .padding(top = 25.dp)
        ) {
            Text(
                text = "Neues Szenario",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            val mapsCamera = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 0f)
            }

            GoogleMap(
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .border(
                        width = 3.5.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(25.dp)
                    ),
                cameraPositionState = mapsCamera,
                mapColorScheme = ComposeMapColorScheme.DARK,
                uiSettings = MapUiSettings(mapToolbarEnabled = false),
                onMapClick = { viewModel.setCurrentLocation(it) }
            ) {
                currentLocationSelection?.let {
                    val markerState = rememberUpdatedMarkerState(position = it)
                    AdvancedMarker(
                        state = markerState,
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_alien)
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Name:",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            /*Row(modifier = Modifier.fillMaxWidth()) {
                Text("Countdown:")
            }*/

            MainGameButton(
                name = "Speichern",
                onClick = {
                    viewModel.saveCurrentCustomLocation()
                    onBackToGameSetup()
                }
            )

        }
    }
}

@Preview
@Composable
fun AddNewLocationpPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            AddNewLocationScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel<AddNewLocationViewModel>()
            )
        }
    }
}