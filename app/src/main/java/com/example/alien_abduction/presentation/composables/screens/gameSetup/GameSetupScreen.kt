package com.example.alien_abduction.presentation.composables.screens.gameSetup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.presentation.composables.customComposables.CircularButton
import com.example.alien_abduction.presentation.sampleData.demoCustomLocation
import com.example.alien_abduction.presentation.sampleData.demoGameConfigMultiplayerRandomLocation
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.sampleData.demoGameConfigRandomLocation
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun GameSetupScreen(
    modifier: Modifier = Modifier,
    viewModel: GameSetupViewModel,
    onGameLaunch: (gameConfiguration: GameConfiguration) -> Unit = {},
    onAddNewLocation: () -> Unit = {}
) {

    val customLocations by viewModel.customLocations.collectAsState()

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
                style = MaterialTheme.typography.bodyLarge,
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                val modeConfigurationModifier = Modifier
                    .matchParentSize()
                    .padding(bottom = 100.dp, top = 16.dp)
                when (viewModel.gameMode) {
                    GameMode.CLASSIC -> ClassicGameSetup(modeConfigurationModifier)
                    GameMode.EXPLORE -> ExploreGameSetup(modeConfigurationModifier)
                    GameMode.MULTIPLAYER -> MultiplayerGameSetup(modeConfigurationModifier, viewModel)
                    GameMode.CHALLENGE -> ChallengeGameSetup(modeConfigurationModifier, viewModel)
                }


            }
        }

        //start game button
        Button(
            onClick = { onGameLaunch(viewModel.buildGameConfiguration())},
            content = {
                Text(text = "Start", fontSize = 25.sp)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .size(width = 150.dp, height = 70.dp)
        )

        if(viewModel.gameMode == GameMode.CHALLENGE)
            CircularButton(
                onClick = { onAddNewLocation() },
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 10.dp)
                    .size(70.dp)
            )
    }
}

@Preview
@Composable
fun GameSetupPreview() {
    AlienabductionTheme {
        val mode = GameMode.CHALLENGE
        Scaffold { innerPadding ->
            GameSetupScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel<GameSetupViewModel>(
                    factory = GameSetupViewModelFactory(mode)
                )
            )
        }
    }
}

