package com.example.alien_abduction.presentation.composables.screens.menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alien_abduction.R
import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.PlayerGuess
import com.example.alien_abduction.domain.dataModels.PlayerResult
import com.example.alien_abduction.domain.util.formatDistanceToMetric
import com.example.alien_abduction.domain.util.getPlayerMarker
import com.example.alien_abduction.presentation.composables.customComposables.MainGameButton
import com.example.alien_abduction.presentation.sampleData.demoGameData
import com.example.alien_abduction.presentation.viewModels.ResultScreenViewModel
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    viewModel: ResultScreenViewModel,
    onReturnToMenu: () -> Unit = {}
) {
    val mapsCamera = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.alienLocation, 0f)
    }

    val playerResults by viewModel.playerResults.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
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
        ) {

            AdvancedMarker(
                state = rememberUpdatedMarkerState(position = viewModel.alienLocation),
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_alien)
            )

            viewModel.gameData.playerGuesses.forEach {

                val playerGuess = LatLng(it.latitude, it.longitude)

                AdvancedMarker(
                    state = rememberUpdatedMarkerState(position = playerGuess),
                    icon = BitmapDescriptorFactory.fromResource(getPlayerMarker(it.playerSlot))
                )

                Polyline(
                    points = listOf(viewModel.alienLocation, playerGuess),
                    color = Color.White,
                    pattern = listOf(Dash(15f), Gap(15f)),
                )
            }

        }

        PlayerResultsList(
            playerResults = playerResults,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )


        MainGameButton(
            name = "Zurück zum Hauptmenü",
            onClick = { onReturnToMenu() }
        )

    }
}

@Composable
fun PlayerResultsList(
    modifier: Modifier = Modifier,
    playerResults: List<PlayerResult>
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.padding(bottom = 5.dp)) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Spieler", style = MaterialTheme.typography.bodyMedium)
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Entfernung", style = MaterialTheme.typography.bodyMedium)
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(0.5f)
            ) {
                Text(text = "Punkte", style = MaterialTheme.typography.bodyMedium)
            }
        }
        playerResults.forEach {
            PlayerResult(
                playerResult = it,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }

    }
}

@Composable
fun PlayerResult(
    modifier: Modifier = Modifier,
    playerResult: PlayerResult
) {
    Row(
        modifier = modifier
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 7.dp)
                    .size(9.dp)
                    .background(
                        shape = CircleShape,
                        color = playerResult.playerGuess.playerSlot.color
                    )
            )
            Text(
                text = playerResult.playerGuess.playerName,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = formatDistanceToMetric(playerResult.proximity),
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier.weight(0.5f),
            text = playerResult.points.toString(),
            textAlign = TextAlign.End,
            fontSize = 20.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ResultScreenPreview() {
    AlienabductionTheme {
        /*Scaffold { innerPadding ->
            ResultScreen(
                viewModel = ResultScreenViewModel(
                    gameData = demoGameData,
                ),
                modifier = Modifier.padding(innerPadding)
            )
        }*/
    }
}