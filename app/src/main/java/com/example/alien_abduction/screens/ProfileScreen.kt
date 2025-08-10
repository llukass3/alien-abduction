package com.example.alien_abduction.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alien_abduction.ui.theme.AlienHeadingFont
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Profile Screen",
            fontFamily = AlienHeadingFont
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            ProfileScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}