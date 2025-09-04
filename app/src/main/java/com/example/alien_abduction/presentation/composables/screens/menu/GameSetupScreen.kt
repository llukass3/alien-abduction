package com.example.alien_abduction.presentation.composables.screens.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.viewModels.demoGameConfig
import com.example.alien_abduction.presentation.composables.gameModes.ChallengeGameSetup
import com.example.alien_abduction.presentation.composables.gameModes.ClassicGameSetup
import com.example.alien_abduction.presentation.composables.gameModes.ExploreGameSetup
import com.example.alien_abduction.presentation.composables.gameModes.MultiplayerGameSetup
import com.example.alien_abduction.presentation.viewModels.demoGameConfigRandomLocation

@Composable
fun GameSetupScreen(
    modifier: Modifier = Modifier,
    viewModel: GameSetupViewModel,
    onGameLaunch: (gameConfiguration: GameConfiguration) -> Unit = {}
) {
    Box(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            Text(
                text = viewModel.gameModeData.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Text(
                text = stringResource(id = viewModel.gameModeData.descriptionRes),
                style = MaterialTheme.typography.bodyLarge
            )
            Box(
                modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 100.dp, top = 16.dp)
            ) {
                when (viewModel.gameMode) {
                    GameMode.CLASSIC -> ClassicGameSetup()
                    GameMode.EXPLORE -> ExploreGameSetup()
                    GameMode.MULTIPLAYER -> MultiplayerGameSetup()
                    GameMode.CHALLENGE -> ChallengeGameSetup()
                }
            }
        }

        //start game button
        Button(
            onClick = { onGameLaunch(/*viewModel.buildGameConfiguration()*/ demoGameConfigRandomLocation) },
            content = {
                Text(text = "Start", fontSize = 25.sp)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(width = 150.dp, height = 80.dp)
                .padding(bottom = 16.dp)
        )
    }
}
/*
@Preview
@Composable
fun GameSetupPreview() {
    AlienabductionTheme {
        val mode = GameMode.EXPLORE
        Scaffold { innerPadding ->
            GameSetupScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = GameSetupViewModel(mode)
            )
        }
    }
}
*/

