package com.example.alien_abduction.presentation.composables.screens.mainGameScreenComposables

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng

@Composable
fun BottomGameBar(
    modifier: Modifier = Modifier,
    isMapOpened: Boolean,
    currentGuess: LatLng? = null,
    timerFinished: Boolean = false,
    onGuessFinished: () -> Unit = {},
    openMap: () -> Unit = {},
    closeMap: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        val context = LocalContext.current

        val regularButtonColors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        val onGuessButtonColors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        )

        var buttonColors by remember { mutableStateOf(regularButtonColors) }
        buttonColors = if (currentGuess != null && isMapOpened) onGuessButtonColors else regularButtonColors

        GuessButton(
            modifier = Modifier.align(Alignment.Center),
            colors = buttonColors,
            onClick = {
                when {
                    isMapOpened && currentGuess == null -> {
                        Toast.makeText(
                            context,
                            "Keinen Standort ausgewählt",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    isMapOpened && currentGuess != null -> {
                        onGuessFinished()
                    }
                    else -> openMap()
                }
            }
        )

        if (isMapOpened && !timerFinished) {
            CloseMapButton(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
                    .offset(120.dp),
                onClick = { closeMap() }
            )
        }
    }
}

@Composable
fun GuessButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        content = {
            Text(text = "Guess", style = MaterialTheme.typography.headlineLarge)
        }
    )
}

@Composable
fun CloseMapButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color.Black
        )
    }
}