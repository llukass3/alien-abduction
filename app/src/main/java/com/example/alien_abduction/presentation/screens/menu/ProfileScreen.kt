package com.example.alien_abduction.presentation.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alien_abduction.R
import com.example.alien_abduction.presentation.customComposables.MainGameButton
import com.example.alien_abduction.presentation.customComposables.MainGameCard
import com.example.alien_abduction.ui.theme.AlienDarkGray
import com.example.alien_abduction.ui.theme.AlienLightGray
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 25.dp)
    ){
        UserProfile()
        UserStats(
            modifier = Modifier
                .width(300.dp)
                .padding(top = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MainGameCard(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                painterResourceId = R.drawable.game_history,
                onClick = {}
            )
            MainGameCard(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                painterResourceId = R.drawable.badge,
                onClick = {}
            )
        }
    }
}

@Composable
fun UserProfile() {
    Image(
        painterResource(R.drawable.empty_pfp),
        contentDescription = "profile picture",
        modifier = Modifier
            .size(190.dp)
            .clip(CircleShape)
            .clickable {  }
    )

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 16.dp)
            .clickable {  }
    ){
        Text(
            text = "Specimen #1261",
            style = MaterialTheme.typography.titleLarge
        )
        Icon(
            painter = painterResource(R.drawable.pen),
            contentDescription = "edit profile",
            modifier = Modifier
                .size(30.dp)
                .padding(start = 5.dp)
        )
    }
}

@Composable
fun UserStats(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        val dividerModifier: Modifier = Modifier.padding(bottom = 3.dp, top = 3.dp)

        UserStatsRow(label = "Gespielte Runden", value = "12",)
        HorizontalDivider(color = AlienDarkGray, thickness = 1.5.dp, modifier = dividerModifier)
        UserStatsRow(label = "Durchschn. Genauigkeit", value = "400.23",)
        HorizontalDivider(color = AlienDarkGray, thickness = 1.5.dp, modifier = dividerModifier)
        UserStatsRow(label = "Bestes Ergebnis", value = "10.25",)
        HorizontalDivider(color = AlienDarkGray, thickness = 1.5.dp, modifier = dividerModifier)
        UserStatsRow(label = "Lieblingsmodus", value = "Klassisch",)
    }
}

@Composable
fun UserStatsRow(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 3.dp)
    ) {
        Text("${label}:")
        Text(text = value, fontWeight = FontWeight.SemiBold)
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