package com.example.alien_abduction.presentation.customComposables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.R

@Composable
fun MainGameCard(
    modifier: Modifier = Modifier,
    painterResourceId: Int,
    onClick: () -> Unit = {},
) {
    FilledIconButton(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        modifier = modifier
            .fillMaxHeight() // fill the parent’s height
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            contentDescription = "settings",
            modifier = Modifier.size(70.dp)
        )
    }
}