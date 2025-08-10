package com.example.alien_abduction.customComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.R

@Composable
fun BottomNavBar(){
    HorizontalDivider()
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            //.background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .height(65.dp)
            .padding(top = 15.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.joystick),
            contentDescription = "Play",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.height(40.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Play",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.height(40.dp),
        )
    }
}