package com.example.alien_abduction.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.google.maps.android.StreetViewUtils.Companion.fetchStreetViewData
import com.example.alien_abduction.BuildConfig
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.domain.viewModels.MainGameViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(MapsExperimentalFeature::class)
@Composable
fun MainGameScreen(
    modifier: Modifier = Modifier,
    viewModel: MainGameViewModel
) {
    val singapore = LatLng(1.35, 103.87)
    var streetViewResult by remember { mutableStateOf(Status.NOT_FOUND) }

    val streetViewCamera = rememberStreetViewCameraPositionState()
    val mapsCamera = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
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
        launch {
            streetViewResult =
                fetchStreetViewData(singapore, BuildConfig.MAPS_API_KEY)
        }
    }
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        if (streetViewResult == Status.OK) {
            StreetView(
                Modifier.matchParentSize(),
                cameraPositionState = streetViewCamera,
                streetViewPanoramaOptionsFactory = {
                    StreetViewPanoramaOptions()
                        .position(singapore)
                },
            )
        } else {
            //executes if location offers no StreetView data
            Text(
                text = "Location not available.",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if(false) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                GoogleMap(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(horizontal = 15.dp, vertical = 70.dp)
                        .clip(shape = RoundedCornerShape(25.dp))
                        .border(width = 3.5.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(25.dp)),
                    cameraPositionState = mapsCamera
                )
            }
        }

        CountdownCounter(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 10.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .height(50.dp),
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
            MainGameScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel<MainGameViewModel>()
            )
        }
    }
}