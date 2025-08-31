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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.alien_abduction.domain.navigation.AchievementsScreen
import com.example.alien_abduction.domain.navigation.GameHistoryScreen
import com.example.alien_abduction.domain.navigation.GameSetup
import com.example.alien_abduction.domain.navigation.HomeScreen
import com.example.alien_abduction.domain.navigation.ProfileScreen
import com.example.alien_abduction.domain.navigation.MainGameScreen
import com.example.alien_abduction.domain.viewModels.GameSetupViewModel
import com.example.alien_abduction.domain.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.domain.viewModels.MainGameViewModel
import com.example.alien_abduction.domain.viewModels.MainGameViewModelFactory
import com.example.alien_abduction.presentation.customComposables.BottomNavBar
import com.example.alien_abduction.presentation.screens.game.MainGameScreen
import com.example.alien_abduction.presentation.screens.menu.AchievementsScreen
import com.example.alien_abduction.presentation.screens.menu.GameHistoryScreen
import com.example.alien_abduction.presentation.screens.menu.GameSetupScreen
import com.example.alien_abduction.presentation.screens.menu.HomeScreen
import com.example.alien_abduction.presentation.screens.menu.ProfileScreen
import com.example.alien_abduction.ui.theme.AlienabductionTheme


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
                        composable<HomeScreen> {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                onModeChosen = { gameMode -> navController.navigate(GameSetup(gameMode)) },
                            )
                        }
                        composable<GameSetup> {
                            val args = it.toRoute<GameSetup>()
                            GameSetupScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<GameSetupViewModel>(
                                    factory = GameSetupViewModelFactory(args.gameMode)
                                ),
                                onGameLaunch = { gameConfig -> navController.navigate(MainGameScreen(gameConfig)) }
                            )
                        }
                        composable<MainGameScreen> {
                            val args = it.toRoute<MainGameScreen>()
                            MainGameScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<MainGameViewModel>(
                                    factory = MainGameViewModelFactory(args.gameConfiguration)
                                )
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

