package com.example.alien_abduction

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.room.Room
import com.example.alien_abduction.data.AppDatabase
import com.example.alien_abduction.data.repositoryImplementations.CustomLocationRepositoryImpl
import com.example.alien_abduction.data.repositoryImplementations.GameHistoryRepositoryImpl
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
import com.example.alien_abduction.data.repositoryImplementations.StreetViewLocationsRepositoryImpl
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.GameData
import com.example.alien_abduction.domain.navigation.AddNewLocationScreen
import com.example.alien_abduction.domain.navigation.AppInfoDetail
import com.example.alien_abduction.domain.navigation.AppInfoMenu
import com.example.alien_abduction.domain.navigation.ResultScreen
import com.example.alien_abduction.domain.useCases.GetStreetViewLocationsUseCase
import com.example.alien_abduction.domain.useCases.SelectRandomLocationUseCase
import com.example.alien_abduction.domain.useCases.TimerUseCase
import com.example.alien_abduction.presentation.composables.screens.gameSetup.AddNewLocationScreen
import com.example.alien_abduction.presentation.composables.screens.menu.AppInfo.AppInfoDetailedView
import com.example.alien_abduction.presentation.composables.screens.menu.AppInfo.AppInfoScreen
import com.example.alien_abduction.presentation.composables.screens.menu.ResultScreen
import com.example.alien_abduction.presentation.viewModels.AddNewLocationViewModel
import com.example.alien_abduction.presentation.viewModels.AddNewLocationViewModelFactory
import com.example.alien_abduction.presentation.viewModels.GameHistoryViewModel
import com.example.alien_abduction.presentation.viewModels.GameHistoryViewModelFactory
import com.example.alien_abduction.presentation.viewModels.ResultScreenViewModel
import com.example.alien_abduction.presentation.viewModels.ResultScreenViewModelFactory

class MainActivity : ComponentActivity() {

    //Room-Database for persistent saving of game history
    lateinit var database: AppDatabase
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            //Set system bars background color to transparent
            statusBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT)
        )

        setContent {
            AlienabductionTheme {
                //navigation related variables
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen = navBackStackEntry?.destination?.route //saves current screen

                //database related variables
                val database = Room.databaseBuilder(
                    LocalContext.current,
                    AppDatabase::class.java,
                    "alien_abduction_db")
                    .fallbackToDestructiveMigration(false).build()
                val gameHistoryDao = database.gameHistoryDao()
                val customLocationDao = database.customLocationDao()

                //repositories
                val gameHistoryRepo = GameHistoryRepositoryImpl(gameHistoryDao) //accesses game history data
                val locationsRepo = StreetViewLocationsRepositoryImpl(LocalContext.current) //all locations in the game
                val customLocationRepo = CustomLocationRepositoryImpl(customLocationDao) //custom locations

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
                    //NavHost for navigation between screens
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreen
                    ) {

                        /*The Home Screen is the first Screen the player lands on
                        * The Player Can choose a game Mode here.*/

                        composable<HomeScreen> {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                onModeChosen = { gameMode -> navController.navigate(GameSetup(gameMode)) },
                                onViewInfo = {navController.navigate(AppInfoMenu)}
                            )
                        }

                        /*The Home Screen features statistics and navigates to the game history screen
                        * and the achievements screen*/
                        composable<ProfileScreen> {
                            ProfileScreen(
                                modifier = Modifier.padding(innerPadding),
                                navToGameHistory = { navController.navigate(GameHistoryScreen) },
                                navToAchievements = { navController.navigate(AchievementsScreen) }
                            )
                        }

                        /*The Game History Screen features a List previously completed Games*/
                        composable<GameHistoryScreen> {
                            GameHistoryScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<GameHistoryViewModel>(
                                    factory = GameHistoryViewModelFactory(gameHistoryRepo)
                                )
                            )
                        }

                        /*The Achievement screen features a List of achievements the player can unlock*/
                        composable<AchievementsScreen> {
                            AchievementsScreen(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        /*The Game Setup features a short description for each gamemode and lets
                        players customize their games. Different for each game Mode*/
                        composable<GameSetup> {
                            val args = it.toRoute<GameSetup>()
                            GameSetupScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<GameSetupViewModel>(
                                    factory = GameSetupViewModelFactory(
                                        gameMode = args.gameMode,
                                        customLocationRepo = customLocationRepo
                                    )
                                ),
                                onGameLaunch = { gameConfiguration ->
                                    val gameConfigJson = Json.encodeToString(gameConfiguration)
                                    navController.navigate(MainGameScreen(gameConfigJson))
                                },
                                onAddNewLocation = {
                                    navController.navigate(AddNewLocationScreen)
                                }
                            )
                        }

                        /*In this screen the player can add a new custom location to the game.*/
                        composable<AddNewLocationScreen> {
                            AddNewLocationScreen(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel<AddNewLocationViewModel>(
                                    factory = AddNewLocationViewModelFactory(customLocationRepo)
                                ),
                                onBackToGameSetup = {
                                    navController.navigate(GameSetup(GameMode.CHALLENGE))
                                }
                            )
                        }

                        /*The main in-game screen where the player navigates the street view and guesses
                        * a location on the map*/
                        composable<MainGameScreen> {
                            val args = it.toRoute<MainGameScreen>()
                            val gameConfiguration = Json.decodeFromString<GameConfiguration>(args.gameConfigJson)

                            val getStreetViewLocationsUseCase = GetStreetViewLocationsUseCase(locationsRepo)
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
                                onGameComplete = { gameData ->
                                    val gameDataJson = Json.encodeToString(gameData)
                                    navController.navigate(ResultScreen(gameDataJson))
                                }
                            )
                        }

                        /*The Result Screen shows the player the results of the game.*/
                        composable<ResultScreen> {
                            val args = it.toRoute<ResultScreen>()
                            val gameData = Json.decodeFromString<GameData>(args.gameDataJson)

                            ResultScreen(
                                modifier = Modifier.padding(innerPadding),
                                onReturnToMenu = { navController.navigate(HomeScreen) },
                                viewModel = viewModel<ResultScreenViewModel>(
                                    factory = ResultScreenViewModelFactory(
                                        gameData = gameData,
                                        gameHistoryRepo = gameHistoryRepo
                                    )
                                )
                            )
                        }

                        composable<AppInfoMenu> {
                            AppInfoScreen(
                                modifier = Modifier.padding(innerPadding),
                                onNavToDetailView = { navController.navigate(AppInfoDetail(it)) }
                            )
                        }

                        composable<AppInfoDetail> {
                            val args = it.toRoute<AppInfoDetail>()
                            AppInfoDetailedView(
                                modifier = Modifier.padding(innerPadding),
                                screenId = args.screenId
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

