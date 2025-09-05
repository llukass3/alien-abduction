package com.example.alien_abduction.presentation.composables.screens.game

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.Status
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.compose.streetview.rememberStreetViewCameraPositionState
import com.google.maps.android.ktx.MapsExperimentalFeature
import kotlinx.coroutines.launch
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.presentation.viewModels.MainGameViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.alien_abduction.presentation.composables.customComposables.mapComposables.MainGameMap

@OptIn(MapsExperimentalFeature::class)
@Composable
fun MainGameScreen(
    modifier: Modifier = Modifier,
    viewModel: MainGameViewModel,
    onGuessFinished: () -> Unit = {}
) {
    val initialLocation by viewModel.initialLocation.collectAsState()
    val streetViewStatus by viewModel.streetViewStatus.collectAsState()
    var isMapOpened by remember { mutableStateOf(false) }
    val currentGuess by viewModel.currentGuess.collectAsState()

    val streetViewCamera = rememberStreetViewCameraPositionState()
    val mapsCamera = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(41.0, 6.0), 0f)
    }

    LaunchedEffect(streetViewCamera) {
        launch {
            snapshotFlow { streetViewCamera.panoramaCamera }
                .collect {

                }
        }
        launch {
            snapshotFlow { streetViewCamera.location }
                .collect {

                }
        }
    }
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        if (streetViewStatus == Status.OK) {
            StreetView(
                Modifier.matchParentSize(),
                cameraPositionState = streetViewCamera,
                isStreetNamesEnabled = false,
                streetViewPanoramaOptionsFactory = {
                    StreetViewPanoramaOptions()
                        .position(initialLocation)
                },
            )

            if(isMapOpened) {
                MainGameMap(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    cameraPositionState = mapsCamera,
                    onMapClick = {viewModel.setCurrentGuess(it)},
                    currentGuess = currentGuess
                )
            }

            CountdownCounter(modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
                    .height(50.dp),
            ) {
                val context = LocalContext.current
                GuessButton(
                    modifier = Modifier,
                    onClick = {
                        when {
                            isMapOpened && currentGuess == null -> {
                                Toast.makeText(
                                    context,
                                    "Keinen Standort ausgewählt",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            isMapOpened && currentGuess != null -> {
                                onGuessFinished()
                            }
                            else -> {
                                isMapOpened = true
                            }
                        }
                    }
                )
                if (isMapOpened) {
                    CloseMapButton(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                            .offset(120.dp),
                        onClick = { isMapOpened = false }
                    )
                }
            }


        }
        else {
            Text("Loading Location", modifier = Modifier.align(Alignment.Center))
        }

    }
}



@Composable
fun GuessButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        content = {
            Text(text = "Guess", style = MaterialTheme.typography.headlineLarge)
        }
    )
}

@Composable
fun CloseMapButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color.Black
        )
    }
}

@Composable
fun CountdownCounter(modifier: Modifier = Modifier) {
    val roundedCornerShape = RoundedCornerShape(25.dp)
    Box(modifier = modifier
        .size(width = 150.dp, height = 50.dp)
        .background(
            color = MaterialTheme.colorScheme.secondary,
            shape = roundedCornerShape
        )
        //.border(width = 3.5.dp, color = MaterialTheme.colorScheme.onPrimary, shape = roundedCornerShape)
    ) {
        Text(
            text = "00:59",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun StreetViewScreenPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                CountdownCounter(modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp)
                        .height(50.dp),
                ) {
                    GuessButton(
                        modifier = Modifier
                    )
                    IconButton(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                            .offset(100.dp),
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}