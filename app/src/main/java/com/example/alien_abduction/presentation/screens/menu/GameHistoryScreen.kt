package com.example.alien_abduction.presentation.screens.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.example.alien_abduction.ui.theme.bodyMediumBold

@Composable
fun GameHistoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 25.dp)
    ) {
        Text(
            text = "Spielverlauf",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            userScrollEnabled = true,
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp)
        ) {
            item {
                DefaultGameHistoryEntry()
            }
            item {
                DefaultGameHistoryEntry()
            }
            item {
                DefaultGameHistoryEntry()
            }
            item {
                DefaultGameHistoryEntry()
            }
            item {
                DefaultGameHistoryEntry()
            }
        }
    }
}

@Composable
fun DefaultGameHistoryEntry(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
    ) {
        Box(Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 20.dp)
        ) {
            Text(
                text = "Herausforderung",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.TopStart),
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                        append("27.08.2025 20:25\n")
                    }
                    withStyle(style = MaterialTheme.typography.bodyMediumBold.toSpanStyle()) {
                        append("Spielzeit: ")
                    }
                    withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                        append("4 min.\n")
                    }
                    withStyle(style = MaterialTheme.typography.bodyMediumBold.toSpanStyle()) {
                        append("Ergebnis: ")
                    }
                    withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                        append("1352.50")
                    }
                },
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodyMediumBold.toSpanStyle()) {
                        append("Standort\n")
                    }
                    withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                        append("51°03'56.2\"N\n")
                    }
                    withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                        append("7°21'52.9\"E")
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 40.dp)
            )
        }
    }
}




@Preview
@Composable
fun GameHistoryScreePreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            GameHistoryScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}