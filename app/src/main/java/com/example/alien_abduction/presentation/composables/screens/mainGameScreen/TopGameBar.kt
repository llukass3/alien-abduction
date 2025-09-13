package com.example.alien_abduction.presentation.composables.screens.mainGameScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun TopGameBar(
    modifier: Modifier = Modifier,
    timeLeft: Float? = null,
    currentRound: Int,
    maxRounds: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
    ) {
        RoundCounter(
            modifier = Modifier
                .size(width = 80.dp, height = 40.dp),
            currentRound = currentRound,
            maxRounds = maxRounds
        )

        CountdownCounter(
            modifier = Modifier,
            timeLeft = timeLeft
        )

        CurrentPlayerLabel(
            modifier = Modifier
                .size(width = 80.dp, height = 40.dp),
            currentPlayer = "Lukas",
            playerColor = Color.Blue
        )
    }
}

@Composable
fun CountdownCounter(
    modifier: Modifier = Modifier,
    timeLeft: Float? = null
) {
    val roundedCornerShape = RoundedCornerShape(25.dp)

    val displayTime = if (timeLeft != null) {
        formatSecondsToMMSS(timeLeft)
    } else "\u221E"

    Box(modifier = modifier
        .size(width = 150.dp, height = 50.dp)
        .background(
            color = MaterialTheme.colorScheme.secondary,
            shape = roundedCornerShape
        )
        //.border(width = 3.5.dp, color = MaterialTheme.colorScheme.onPrimary, shape = roundedCornerShape)
    ) {
        Text(
            text = displayTime,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun formatSecondsToMMSS(seconds: Float): String {
    val totalSeconds = seconds.toInt()
    val minutes = totalSeconds / 60
    val secs = totalSeconds % 60
    return String.format(Locale.US, "%02d:%02d", minutes, secs)
}

@Composable
fun RoundCounter(
    modifier: Modifier = Modifier,
    currentRound: Int,
    maxRounds: Int
) {
    val roundedCornerShape = RoundedCornerShape(25.dp)

    Box(modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.secondary,
            shape = roundedCornerShape
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            text = "$currentRound / $maxRounds"
        )
    }
}

@Composable
fun CurrentPlayerLabel(
    modifier: Modifier = Modifier,
    currentPlayer: String,
    playerColor: Color
) {
    val roundedCornerShape = RoundedCornerShape(25.dp)

    Box(modifier = modifier
        .background(
            color = playerColor,
            shape = roundedCornerShape
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            text = currentPlayer
        )
    }
}