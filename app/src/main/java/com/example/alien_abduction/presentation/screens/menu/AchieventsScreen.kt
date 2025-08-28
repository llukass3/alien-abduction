package com.example.alien_abduction.presentation.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.R
import com.example.alien_abduction.domain.viewModels.AchievementsViewModel
import com.example.alien_abduction.presentation.customComposables.MainGameCard
import com.example.alien_abduction.ui.theme.AlienGreen
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun AchievementsScreen(modifier: Modifier = Modifier) {

    val viewModel: AchievementsViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 25.dp)
    ) {
        Text(
            text = "Abzeichen",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            userScrollEnabled = true,
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp)
        ) {
            item {
                AchievementCard(
                    painterResourceId = R.drawable.badge_iron,
                    contentDescription = "iron badge",
                    modifier = Modifier.height(150.dp)
                )
            }
            item {
                AchievementCard(
                    painterResourceId = R.drawable.badge_bronze,
                    contentDescription = "bronze badge",
                    modifier = Modifier.height(150.dp)
                )
            }
            item {
                AchievementCard(
                    painterResourceId = R.drawable.badge_silver,
                    contentDescription = "silver badge",
                    modifier = Modifier.height(150.dp)
                )
            }
            item {
                AchievementCard(
                    painterResourceId = R.drawable.badge_gold,
                    contentDescription = "gold badge",
                    modifier = Modifier.height(150.dp)
                )
            }
            item {
                AchievementCard(
                    painterResourceId = R.drawable.badge_platinum,
                    contentDescription = "platinum badge",
                    modifier = Modifier.height(150.dp)
                )
            }
            item {
                AchievementCard(
                    painterResourceId = R.drawable.badge_diamond,
                    contentDescription = "diamond badge",
                    modifier = Modifier.height(150.dp)
                )
            }
        }
    }
}

@Composable
fun AchievementCard(
    modifier: Modifier = Modifier,
    painterResourceId: Int,
    contentDescription: String? = null,
    locked: Boolean = true,
    onClick: () -> Unit = {},
) {
    FilledIconButton(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        modifier = modifier
            .fillMaxHeight() // fill the parent’s height
    ) {
        Image(
            painter = painterResource(id = painterResourceId),
            contentDescription = contentDescription,
            modifier = Modifier.size(120.dp)
        )
        if (locked) {
            Icon(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(60.dp)
                    .alpha(0.7f)
            )
        }
    }
}

@Composable
fun AchievementPopUp() {

}

@Preview
@Composable
fun AchievementsScreenPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            AchievementsScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}