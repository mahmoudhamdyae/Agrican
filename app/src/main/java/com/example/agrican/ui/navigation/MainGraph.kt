package com.example.agrican.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.home.main.MainDestination
import com.example.agrican.ui.screens.home.main.MainScreen
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertScreen
import com.example.agrican.ui.screens.home.main.ask_expert.ChatDestination
import com.example.agrican.ui.screens.home.main.ask_expert.ChatScreen
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorScreen
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesScreen
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherScreen

@Composable
fun MainGraph(
    setTopBarTitle: (Int) -> Unit,
    setTopBarIcon: (Boolean) -> Unit,
    showTopBar: (Boolean) -> Unit,
    showBottomBar: (Boolean) -> Unit,
) {

    val navController = rememberNavController()

    val openScreen: (String) -> Unit = { route -> navController.navigate(route) { launchSingleTop = true } }
    val openAndClear: (String) -> Unit = { route ->
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
    val openAndPopUp: (String, String) -> Unit = { route, popUp ->
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }
    val navigateUp: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = MainDestination.route
    ) {
        composable(route = MainDestination.route) {
            setTopBarIcon(true)
            showTopBar(true)
            showBottomBar(true)
            MainScreen(openScreen = openScreen)
        }

        composable(route = ProblemImagesDestination.route) {
            showTopBar(true)
            showBottomBar(true)
            setTopBarIcon(false)
            setTopBarTitle(ProblemImagesDestination.titleRes)
            ProblemImagesScreen(
                navigateUp = navigateUp,
                openCamera = {
                    showTopBar(false)
                    showBottomBar(false)
                }
            )
        }

        composable(route = FertilizersCalculatorDestination.route) {
            setTopBarIcon(false)
            setTopBarTitle(FertilizersCalculatorDestination.titleRes)
            FertilizersCalculatorScreen()
        }

        composable(route = AskExpertDestination.route) {
            showTopBar(false)
            showBottomBar(false)
            AskExpertScreen(openAndPopUp = openAndPopUp, navigateUp = navigateUp)
        }

        composable(route = ChatDestination.route) {
            setTopBarIcon(false)
            setTopBarTitle(ChatDestination.titleRes)
            showTopBar(true)
            showBottomBar(false)
            ChatScreen(navigateUp = navigateUp)
        }

        composable(route = WeatherDestination.route) {
            setTopBarIcon(false)
            setTopBarTitle(WeatherDestination.titleRes)
            WeatherScreen()
        }
    }
}