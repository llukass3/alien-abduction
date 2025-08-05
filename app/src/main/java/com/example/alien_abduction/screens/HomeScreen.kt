package com.example.alien_abduction.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.customComposables.MainGameButton
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun HomeScreen(modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        GameModeList()
    }
}

@Composable
fun BottonNavBar(){

}

@Composable
fun GameModeList(
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        MainGameButton(
            "Classic",
            onClick = { TODO() },
            modifier = Modifier.fillMaxWidth()
        )
        MainGameButton(
            "Challenge",
            onClick = { TODO() },
            modifier = Modifier.fillMaxWidth()
        )
        MainGameButton(
            "Country",
            onClick = { TODO() },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Lorem Ipsum Dolor Sit Amet")
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