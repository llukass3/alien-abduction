package com.example.alien_abduction.customComposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.alien_abduction.ui.theme.AlienBodyFont
import com.example.alien_abduction.ui.theme.AlienHeadingFont

@Composable
fun MainGameButton(
    name: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        content = {
            Text(text = name)
        }
    )
}