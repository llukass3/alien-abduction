package com.example.alien_abduction.presentation.screens.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun AchievementsScreen(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun AchievementsScreenPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            AchievementsScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}