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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.GameModes
import com.example.alien_abduction.domain.navigation.GameSetup
import com.example.alien_abduction.domain.navigation.HomeScreen
import com.example.alien_abduction.domain.navigation.ProfileScreen
import com.example.alien_abduction.presentation.customComposables.BottomNavBar
import com.example.alien_abduction.presentation.customComposables.MainGameButton
import com.example.alien_abduction.presentation.screens.menu.setupScreens.GameSetupScreen
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
                val currentScreen = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        val showBottomBar = when {
                            currentScreen == HomeScreen::class.qualifiedName -> true
                            currentScreen == ProfileScreen::class.qualifiedName -> true
                            currentScreen?.startsWith(GameSetup::class.qualifiedName!!) == true -> true
                            else -> false
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
                                onModeChosen = { navController.navigate(GameSetup(it)) },
                                modifier = Modifier.padding(innerPadding))
                        }
                        composable<GameSetup> {
                            val args = it.toRoute<GameSetup>()
                            GameSetupScreen(
                                modifier = Modifier.padding(innerPadding),
                                mode = GameModes.fromMode(args.gameMode),
                            )
                        }
                        composable<ProfileScreen> {
                            ProfileScreen()
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

