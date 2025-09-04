package com.example.alien_abduction.presentation.composables.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.R
import com.example.alien_abduction.presentation.composables.customComposables.MainGameButton
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onModeChosen: (GameMode) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.splash_art),
            "Alien Abduction Splash Art",
            modifier = Modifier
                .padding(top = 25.dp)
        )
        GameModeList(onModeChosen = { onModeChosen(it) })
    }

}

@Composable
fun GameModeList(
    onModeChosen: (GameMode) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(horizontal = 30.dp,)
    ) {
        Text(
            text = "Wähle einen Spielmodus",
            modifier = Modifier.padding(bottom = 10.dp)
        )
        MainGameButton(
            "Klassisch",
            onClick = { onModeChosen(GameMode.CLASSIC) },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
        MainGameButton(
            "Erkunden",
            onClick = { onModeChosen(GameMode.EXPLORE) },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
        MainGameButton(
            "Mehrspieler",
            onClick = { onModeChosen(GameMode.MULTIPLAYER) },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
        MainGameButton(
            "Herausforderungen",
            onClick = { onModeChosen(GameMode.CHALLENGE) },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}