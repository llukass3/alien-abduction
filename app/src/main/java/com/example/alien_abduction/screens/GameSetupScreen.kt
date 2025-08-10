package com.example.alien_abduction.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alien_abduction.gameLogic.GameModes
import com.example.alien_abduction.ui.theme.AlienHeadingFont
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun GameSetupScreen(gameModes: GameModes, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Setup for ${gameModes.name}",
            fontFamily = AlienHeadingFont
        )
    }
}

@Preview
@Composable
fun GameSetupPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            GameSetupScreen(GameModes.CLASSIC, modifier = Modifier.padding(innerPadding))
        }
    }
}

