package com.example.alien_abduction.presentation.composables.screens.gameSetup

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun ClassicGameSetup(modifier: Modifier = Modifier) {

}

@Composable
fun ExploreGameSetup(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun ModeSpecificGameSetupPreview() {
    AlienabductionTheme {
        val mode = GameMode.MULTIPLAYER
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
