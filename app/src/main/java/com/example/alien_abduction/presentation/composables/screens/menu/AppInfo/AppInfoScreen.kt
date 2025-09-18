package com.example.alien_abduction.presentation.composables.screens.menu.AppInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun AppInfoScreen(
    modifier: Modifier,
    onNavToDetailView: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier
            .align(Alignment.Center)
            .padding(top = 60.dp)
            ) {
            Button(
                onClick = { onNavToDetailView(1) },
                content = {
                    Text(text = "App-Beschreibung", fontSize = 16.sp)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(width = 270.dp, height = 60.dp)
            )
            Button(
                onClick = { onNavToDetailView(2) },
                content = {
                    Text(text = "Benutzung und UX", fontSize = 16.sp)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .size(width = 270.dp, height = 60.dp)
            )
        }
    }
}

@Preview
@Composable
fun AppInfoScreenPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            AppInfoScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}