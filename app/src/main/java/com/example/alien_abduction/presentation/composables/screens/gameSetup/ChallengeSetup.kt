package com.example.alien_abduction.presentation.composables.screens.gameSetup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.R
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.CustomLocation
import com.example.alien_abduction.domain.util.formatLatLngDMS
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import com.google.android.gms.maps.model.LatLng

@Composable
fun ChallengeGameSetup(
    modifier: Modifier = Modifier,
    customLocations: List<CustomLocation>
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                customLocations.forEach() {
                    item {
                        CustomLocationCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(5.dp),
                            customLocation = it,
                            onLocationSelected = {

                            },
                            onViewLocation = {

                            },
                            onDeleteLocation = {

                            }
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun CustomLocationCard(
    modifier: Modifier = Modifier,
    customLocation: CustomLocation,
    onLocationSelected: () -> Unit = {},
    onViewLocation: () -> Unit = {},
    onDeleteLocation: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Box(
            Modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            Text(
                text = customLocation.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.TopStart),
            )
            val location = LatLng(customLocation.latitude, customLocation.longitude)
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 30.dp),
                text = formatLatLngDMS(location)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {onLocationSelected()},
                    content = {
                        Text(text = "Auswählen")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                )

                FilledIconButton(
                    onClick = {onViewLocation()},
                    modifier = Modifier,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.map_marker),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Ort ansehen",
                        modifier = Modifier.size(25.dp),
                    )
                }
                FilledIconButton(
                    onClick = {onDeleteLocation()},
                    modifier = Modifier,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Red,
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.trash_can),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Ort löschen",
                        modifier = Modifier.size(25.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ChallengeSetupPreview() {
    AlienabductionTheme {
        val mode = GameMode.CHALLENGE
        Scaffold { innerPadding ->
            GameSetupScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel<GameSetupViewModel>(
                    factory = GameSetupViewModelFactory(mode)
                )
            )
        }
    }
}