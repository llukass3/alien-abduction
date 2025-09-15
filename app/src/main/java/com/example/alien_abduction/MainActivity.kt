package com.example.alien_abduction

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.alien_abduction.domain.dataModels.GameConfiguration
import com.example.alien_abduction.domain.navigation.AchievementsScreen
import com.example.alien_abduction.domain.navigation.GameHistoryScreen
import com.example.alien_abduction.domain.navigation.GameSetup
import com.example.alien_abduction.domain.navigation.HomeScreen
import com.example.alien_abduction.domain.navigation.MainGameScreen
import com.example.alien_abduction.domain.navigation.ProfileScreen
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.presentation.viewModels.MainGameViewModel
import com.example.alien_abduction.presentation.viewModels.MainGameViewModelFactory
import com.example.alien_abduction.presentation.composables.customComposables.BottomNavBar
import com.example.alien_abduction.presentation.composables.screens.mainGameScreenComposables.MainGameScreen
import com.example.alien_abduction.presentation.composables.screens.menu.AchievementsScreen
import com.example.alien_abduction.presentation.composables.screens.menu.GameHistoryScreen
import com.example.alien_abduction.presentation.composables.screens.gameSetup.GameSetupScreen
import com.example.alien_abduction.presentation.composables.screens.menu.HomeScreen
import com.example.alien_abduction.presentation.composables.screens.menu.ProfileScreen
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.example.alien_abduction.data.StreetViewLocationsRepositoryImpl
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.dataModels.PlayerGuess
import com.example.alien_abduction.domain.navigation.ResultScreen
import com.example.alien_abduction.domain.useCases.GetStreetViewLocationsUseCase
import com.example.alien_abduction.domain.useCases.SelectRandomLocationUseCase
import com.example.alien_abduction.domain.useCases.TimerUseCase
import com.example.alien_abduction.presentation.composables.screens.menu.ResultScreen
import com.example.alien_abduction.presentation.viewModels.ResultScreenViewModel
import com.example.alien_abduction.presentation.viewModels.ResultScreenViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            //Set system bars background color to transparent
            statusBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT)
        )
        setContent {
            AlienabductionTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen = navBackStackEntry?.destination?.route //saves current screen

                Scaffold(
                    bottomBar = {
                        //filter all screens where bottom nav bar should not be shown
                        val showBottomBar = when {
                            currentScreen?.startsWith(MainGameScreen::class.qualifiedName!!) == true -> false
                            currentScreen?.startsWith(ResultScreen::class.qualifiedName!!) == true -> false
                            else -> true
                        }

                        if (showBottomBar) {
                            BottomNavBar(
                                modifier = Modifier.navigationBarsPadding(),
                                navToGame = { navController.navigate(HomeScreen) },
                                navToProfile = { navController.navigate(ProfileScreen) }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreen
                    ) {

                        /* menu-related screens */
                        composable<HomeScreen> {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                onModeChosen = { gameMode -> navController.navigate(GameSetup(gameMode)) },
                            )
                        }
                        composable<ProfileScreen> {
                            ProfileScreen(
                                modifier = Modifier.padding(innerPadding),
                                navToGameHistory = { navController.navigate(GameHistoryScreen) },
                                navToAchievements = { navController.navigate(AchievementsScreen) }
                            )
                        }
                        composable<GameHistoryScreen> {
                            GameHistoryScreen(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable<AchievementsScreen> {
                            AchievementsScreen(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        /* game-related screens */
                        composable<GameSetup> {
                            val args = it.toRoute<GameSetup>()
                            GameSetupScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<GameSetupViewModel>(
                                    factory = GameSetupViewModelFactory(args.gameMode)
                                ),
                                onGameLaunch = { gameConfiguration ->
                                    val gameConfigJson = Json.encodeToString(gameConfiguration)
                                    navController.navigate(MainGameScreen(gameConfigJson)) }
                            )
                        }
                        composable<MainGameScreen> {
                            val args = it.toRoute<MainGameScreen>()
                            val gameConfiguration = Json.decodeFromString<GameConfiguration>(args.gameConfigJson)

                            val repository = StreetViewLocationsRepositoryImpl(LocalContext.current)
                            val getStreetViewLocationsUseCase = GetStreetViewLocationsUseCase(repository)
                            val selectRandomLocationUseCase = SelectRandomLocationUseCase()
                            val timerUseCase = TimerUseCase()

                            MainGameScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<MainGameViewModel>(
                                    factory = MainGameViewModelFactory(
                                        gameConfiguration,
                                        getStreetViewLocationsUseCase,
                                        selectRandomLocationUseCase,
                                        timerUseCase
                                    )
                                ),
                                onGuessFinished = { gameData ->
                                    val gameDataJson = Json.encodeToString(gameData)
                                    navController.navigate(ResultScreen(gameDataJson))
                                }
                            )
                        }

                        composable<ResultScreen> {
                            val args = it.toRoute<ResultScreen>()
                            val gameData = Json.decodeFromString<GameData>(args.gameDataJson)

                            ResultScreen(
                                modifier = Modifier.padding(innerPadding),
                                onReturnToMenu = { navController.navigate(HomeScreen) },
                                viewModel = viewModel<ResultScreenViewModel>(
                                    factory = ResultScreenViewModelFactory(
                                        gameData = gameData
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AlienPreview() {
        AlienabductionTheme {
            Scaffold { innerPadding ->
                HomeScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

