package com.example.alien_abduction.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alien_abduction.GameSetup
import com.example.alien_abduction.NavigationDestination
import com.example.alien_abduction.R
import com.example.alien_abduction.customComposables.BottomNavBar
import com.example.alien_abduction.customComposables.MainGameButton
import com.example.alien_abduction.gameLogic.GameModes
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onModeChosen: (GameModes) -> Unit = {}) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        bottomBar = { BottomNavBar() },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painterResource(R.drawable.splash_art),
                "Alien Abduction Splash Art",
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            GameModeList(onModeChosen = { onModeChosen(it) })
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TopBar(){

}

@Composable
fun GameModeList(
    onModeChosen: (GameModes) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(start = 30.dp, end = 30.dp,)
    ) {
        Text(text = "Wähle einen Spielmodus")
        MainGameButton(
            "Klassisch",
            onClick = { onModeChosen(GameModes.CLASSIC) },
            modifier = Modifier
                .fillMaxWidth()
        )
        MainGameButton(
            "Erkunden",
            onClick = { onModeChosen(GameModes.EXPLORE) },
            modifier = Modifier.fillMaxWidth()
        )
        MainGameButton(
            "Mehrspieler",
            onClick = { onModeChosen(GameModes.MULTIPLAYER) },
            modifier = Modifier.fillMaxWidth()
        )
        MainGameButton(
            "Herausforderungen",
            onClick = { onModeChosen(GameModes.CHALLENGE) },
            modifier = Modifier.fillMaxWidth()
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