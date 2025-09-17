package com.example.alien_abduction.presentation.composables.screens.gameSetup

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.R
import com.example.alien_abduction.presentation.composables.customComposables.MainGameButton
import com.example.alien_abduction.presentation.viewModels.AddNewLocationViewModel
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.Status
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun AddNewLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: AddNewLocationViewModel,
    onBackToGameSetup: () -> Unit = {}
) {

    val currentLocationSelection by viewModel.currentLocationSelection.collectAsState()
    val currentLocationName by viewModel.currentLocationName.collectAsState()
    val streetViewStatus by viewModel.streetViewStatus.collectAsState()

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
                onMapClick = {
                    viewModel.setCurrentLocation(it)
                    viewModel.checkForStreetViewData()
                }
            ) {
                currentLocationSelection?.let {
                    val markerState = rememberUpdatedMarkerState(position = it)
                    AdvancedMarker(
                        state = markerState,
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_alien)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Name:",
                    style = MaterialTheme.typography.titleMedium
                )
                TextField(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    singleLine = true,
                    value = currentLocationName,
                    onValueChange = { viewModel.setCurrentLocationName(it) },
                    colors = TextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White),
                    textStyle = MaterialTheme.typography.titleMedium,
                )
            }

            /*Row(modifier = Modifier.fillMaxWidth()) {
                Text("Countdown:")
            }*/
            val context = LocalContext.current
            MainGameButton(
                modifier = Modifier.padding(bottom = 10.dp),
                name = "Speichern",
                onClick = {
                    if (currentLocationSelection == null) {
                        Toast.makeText(
                            context,
                            "Wähle einen Standort",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (currentLocationName.isBlank()) {
                        Toast.makeText(
                            context,
                            "Gib dem Szenario einen Namen",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (streetViewStatus != Status.OK){
                        Toast.makeText(
                            context,
                            "Keine StreetView Daten an diesem Ort",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.saveCurrentCustomLocation()
                        onBackToGameSetup()
                    }


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