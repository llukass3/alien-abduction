package com.example.alien_abduction.presentation.composables.screens.mainGameScreenComposables

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.graphics.Color
import com.example.alien_abduction.presentation.viewModels.MainGameViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.collectAsState
import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.dataModels.Player

@OptIn(MapsExperimentalFeature::class)
@Composable
fun MainGameScreen(
    modifier: Modifier = Modifier,
    viewModel: MainGameViewModel,
    onGameComplete: (GameData) -> Unit = {}
) {
    val initialLocation by viewModel.initialLocation.collectAsState()
    val streetViewStatus by viewModel.streetViewStatus.collectAsState()
    val currentGuess by viewModel.currentGuess.collectAsState()
    val currentPlayer by viewModel.currentPlayer.collectAsState()

    val currentRound by viewModel.currentRound.collectAsState()
    val timeLeft by viewModel.timeLeft.collectAsState()
    val timerFinished by viewModel.timerFinished.collectAsState()

    val isMapOpened by viewModel.isMapOpened.collectAsState()
    if (timerFinished) viewModel.setMapState(true)

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
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (streetViewStatus == Status.OK) {

            //street view that the player navigates while trying to guess the location
            StreetView(
                Modifier.matchParentSize(),
                cameraPositionState = streetViewCamera,
                isStreetNamesEnabled = false,
                streetViewPanoramaOptionsFactory = {
                    StreetViewPanoramaOptions()
                        .position(initialLocation)
                },
            )

            //google map, where the player guesses the current location
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

            //top bar, shows current round, countdown and current player
            TopGameBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp)
                    .height(60.dp),
                timeLeft = timeLeft,
                currentRound = currentRound,
                maxRounds = viewModel.maxRounds,
                currentPlayer = currentPlayer
            )

            //bottom bar, displays the "guess" button, which opens the Map
            BottomGameBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                    .height(60.dp),
                isMapOpened = isMapOpened,
                currentGuess = currentGuess,
                timerFinished = timerFinished,
                onGuessFinished = {
                    //save current guess
                    viewModel.saveCurrentGuess()
                    //move to next player, if one exists or end game and expose game data
                    if(viewModel.hasNextPlayer())
                        viewModel.nextPlayer()
                    else onGameComplete(viewModel.buildGameData()!!)
                },
                openMap = { viewModel.setMapState(true) },
                closeMap = { viewModel.setMapState(false) }
            )
        }
        else {
            Text("Loading Location", modifier = Modifier.align(Alignment.Center))
        }

    }
}

@Preview
@Composable
fun StreetViewScreenPreview() {
    AlienabductionTheme {
        Scaffold(

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

                TopGameBar(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 15.dp, end = 15.dp, top = 10.dp),
                    timeLeft = 20f,
                    currentRound = 1,
                    maxRounds = 5,
                    currentPlayer = Player(PlayerSlot.PLAYER_1, "Lukas")
                )

                BottomGameBar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
                    isMapOpened = false,
                    timerFinished = false,
                    onGuessFinished = { },
                    openMap = { },
                    closeMap = { }
                )

            }
        }
    }
}

