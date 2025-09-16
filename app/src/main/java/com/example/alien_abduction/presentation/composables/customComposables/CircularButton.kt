package com.example.alien_abduction.presentation.composables.customComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CircularButton(
    modifier: Modifier = Modifier,
    text: String = "+",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(
                shape = CircleShape,
                color = Color.White
            )
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
