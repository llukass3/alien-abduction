package com.example.alien_abduction.presentation.gameModes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun ClassicGameSetup(modifier: Modifier = Modifier) {
    Text(text = "Classic")
}

@Composable
fun ExploreGameSetup(modifier: Modifier = Modifier) {
    Text(text = "Explore")
}

@Composable
fun MultiplayerGameSetup(modifier: Modifier = Modifier) {
    Text(text = "Multiplayer")
}

@Composable
fun ChallengeGameSetup(modifier: Modifier = Modifier) {
    Text(text = "Challenge")
}