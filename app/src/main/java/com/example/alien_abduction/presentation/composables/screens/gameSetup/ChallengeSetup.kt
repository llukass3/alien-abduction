package com.example.alien_abduction.presentation.composables.screens.gameSetup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.R
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.CustomLocation
import com.example.alien_abduction.domain.util.formatLatLngDMS
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.google.android.gms.maps.model.LatLng
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun ChallengeGameSetup(
    modifier: Modifier = Modifier,
    viewModel: GameSetupViewModel
) {
    val customLocations by viewModel.customLocations.collectAsState()
    var mapOpen by remember {mutableStateOf(false)}
    var selectedLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }

    Box(modifier = modifier) {

        if(!mapOpen && customLocations.isNotEmpty()) {
            LazyColumn (
                modifier = Modifier
            ) {
                customLocations.forEach() {
                    item {
                        CustomLocationCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(5.dp),
                            customLocation = it,
                            onLocationSelected = {
                                viewModel.setInitialLocation(
                                    LatLng(it.latitude, it.longitude)
                                )
                            },
                            onViewLocation = {
                                selectedLocation = LatLng(it.latitude, it.longitude)
                                mapOpen = true
                            },
                            onDeleteLocation = {
                                viewModel.removeCustomLocation(it)
                            }
                        )
                    }
                }
            }
        } else {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 50.dp),
                textAlign = TextAlign.Center,
                text = "Du hast noch keine eigenen Standorte erstellt"
            )
        }

        if(mapOpen) {
            val mapsCamera = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(selectedLocation, 2f)
            }
            GoogleMap(
                modifier = Modifier
                    .matchParentSize()
                    .clip(shape = RoundedCornerShape(25.dp))
                    .border(
                        width = 3.5.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(25.dp)
                    ),
                cameraPositionState = mapsCamera,
                mapColorScheme = ComposeMapColorScheme.DARK,
                uiSettings = MapUiSettings(mapToolbarEnabled = false),
            ) {
                AdvancedMarker(
                    state = rememberUpdatedMarkerState(position = selectedLocation),
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_alien)
                )
            }
            FilledIconButton(
                onClick = { mapOpen = false },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 15.dp, end = 15.dp)
                    .size(60.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.back),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Ort ansehen",
                    modifier = Modifier.size(25.dp),
                )
            }
        }
    }


}

@Composable
fun CustomLocationCard(
    modifier: Modifier = Modifier,
    customLocation: CustomLocation,
    onLocationSelected: () -> Unit = {},
    onViewLocation: () -> Unit = {},
    onDeleteLocation: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Box(
            Modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            Text(
                text = customLocation.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.TopStart),
            )
            val location = LatLng(customLocation.latitude, customLocation.longitude)
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 30.dp),
                text = formatLatLngDMS(location)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {onLocationSelected()},
                    content = {
                        Text(text = "Auswählen")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                )

                FilledIconButton(
                    onClick = {onViewLocation()},
                    modifier = Modifier,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.map_marker),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Ort ansehen",
                        modifier = Modifier.size(25.dp),
                    )
                }
                FilledIconButton(
                    onClick = {onDeleteLocation()},
                    modifier = Modifier,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Red,
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.trash_can),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Ort löschen",
                        modifier = Modifier.size(25.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ChallengeSetupPreview() {
    AlienabductionTheme {
        val mode = GameMode.CHALLENGE
        Scaffold { innerPadding ->
            GameSetupScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel<GameSetupViewModel>(
                    factory = GameSetupViewModelFactory(mode)
                )
            )
        }
    }
}