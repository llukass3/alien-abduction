package com.example.alien_abduction.presentation.screens.menu

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.GameModes
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun GameSetupScreen(
    modifier: Modifier = Modifier,
    mode: GameModes,
    modeSpecificContent: @Composable () -> Unit = {},
    onGameLaunch: () -> Unit = {}
) {
    Box(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                text = mode.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Text(
                text = stringResource(id = mode.descriptionRes),
                style = MaterialTheme.typography.bodyLarge
            )
            modeSpecificContent()
        }

        //start game button
        Button(
            onClick = { onGameLaunch() },
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

@Preview
@Composable
fun GameSetupPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            GameSetupScreen(
                modifier = Modifier.padding(innerPadding),
                mode = GameModes.fromMode(GameMode.CHALLENGE),
            )
        }
    }
}

