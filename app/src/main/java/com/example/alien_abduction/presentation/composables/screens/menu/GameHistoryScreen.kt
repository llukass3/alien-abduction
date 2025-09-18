package com.example.alien_abduction.presentation.composables.screens.menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.GameHistoryEntry
import com.example.alien_abduction.domain.dataModels.PlayerResult
import com.example.alien_abduction.domain.util.formatDistanceToMetric
import com.example.alien_abduction.domain.util.formatLatLngDMS
import com.example.alien_abduction.domain.util.formatSecondsToMMSS
import com.example.alien_abduction.presentation.viewModels.GameHistoryViewModel
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.example.alien_abduction.ui.theme.bodyMediumBold
import com.google.android.gms.maps.model.LatLng
import androidx.compose.foundation.lazy.items
import com.example.alien_abduction.presentation.sampleData.demoMultiplayerGameHistoryEntry

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: GameHistoryViewModel
) {
    val gameHistoryEntries by viewModel.gameHistory.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 25.dp)
    ) {
        Text(
            text = "Spielverlauf",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            userScrollEnabled = true,
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp)
        ) {
            items(gameHistoryEntries) { entry ->
                if (entry.gameMode == GameMode.MULTIPLAYER)
                    MultiplayerGameHistoryEntryCard(
                        data = entry,
                        modifier = Modifier
                    )
                else
                    GameHistoryEntryCard(
                        data = entry,
                        modifier = Modifier
                    )
            }
        }
    }
}

@Composable
fun GameHistoryEntryCard(
    data: GameHistoryEntry,
    modifier: Modifier
) {
    val entry = data.playerResults.first()
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Text(
                text = data.gameMode.displayName,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.TopStart),
            )
            Text(
                text = formatLatLngDMS(
                    LatLng(
                        entry.playerGuess.latitude,
                        entry.playerGuess.longitude
                    )
                ),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 35.dp),
            )
            val timeLeft = entry.playerGuess.timeLeft
            Row(modifier = Modifier.align(Alignment.BottomStart)) {
                Column(modifier = Modifier.weight(1f)) {
                    GameHistoryData(value = "${data.date} ${data.timeOfDay}")
                    GameHistoryData(
                        key = "Restzeit",
                        value = if (timeLeft != null) {
                            formatSecondsToMMSS(timeLeft)
                        } else "\u221E"
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    GameHistoryData(key = "Distanz", value = formatDistanceToMetric(entry.proximity))
                    GameHistoryData(key = "Puntke", value = entry.points.toString())
                }
            }

        }
    }
}

@Composable
fun MultiplayerGameHistoryEntryCard(
    data: GameHistoryEntry,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = data.gameMode.displayName,
                    style = MaterialTheme.typography.headlineMedium,
                )
                GameHistoryData(value = "${data.date} ${data.timeOfDay}")
            }


            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                data.playerResults.forEach() {
                    Row(modifier = Modifier
                        .padding(vertical = 2.dp)
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f),
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .size(9.dp)
                                    .background(
                                        shape = CircleShape,
                                        color = it.playerGuess.playerSlot.color
                                    )
                            )
                            Text(
                                text = it.playerGuess.playerName,
                                style = MaterialTheme.typography.bodyMediumBold
                            )
                        }

                        Text(
                            modifier = Modifier.weight(1f),
                            text = formatDistanceToMetric(it.proximity),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            modifier = Modifier.weight(1f),
                            text = it.points.toString() + " Punkte",
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GameHistoryData(
    key: String? = "",
    value: String
) {
    Row (modifier = Modifier.padding(vertical = 4.dp)) {
        if (key != null) {
            if (key.isNotBlank()) {
                Text(
                    text = "$key: ",
                    style = MaterialTheme.typography.bodyMediumBold
                )
            }
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun GameHistoryPlayerData(
    data: PlayerResult
) {
    Row (modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = data.playerGuess.playerName,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun GameHistoryScreePreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            MultiplayerGameHistoryEntryCard(
                data = demoMultiplayerGameHistoryEntry,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}