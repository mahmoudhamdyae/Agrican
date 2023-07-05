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
import com.example.agrican.ui.screens.home.main.problem_images.disease_capture_result.DiseaseCaptureResultDestination
import com.example.agrican.ui.screens.home.main.problem_images.disease_capture_result.DiseaseCaptureResultScreen
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherScreen

@Composable
fun MainGraph(
    setTopBarTitle: (Int) -> Unit,
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
            showBottomBar(true)
            MainScreen(openScreen = openScreen, openAndClear = openAndClear)
        }

        composable(route = ProblemImagesDestination.route) {
            showBottomBar(false)
            ProblemImagesScreen(
                navigateUp = navigateUp,
                openCamera = { showBottomBar(false) },
                openAndPopUp = { openAndPopUp(it, ProblemImagesDestination.route) }
            )
        }

        composable(route = DiseaseCaptureResultDestination.route) {
            showBottomBar(false)
            DiseaseCaptureResultScreen(navigateUp = navigateUp)
        }

        composable(route = FertilizersCalculatorDestination.route) {
            setTopBarTitle(FertilizersCalculatorDestination.titleRes)
            FertilizersCalculatorScreen(navigateUp = navigateUp)
        }

        composable(route = AskExpertDestination.route) {
            showBottomBar(false)
            AskExpertScreen(openAndPopUp = openAndPopUp)
        }

        composable(route = ChatDestination.route) {
            ChatScreen(navigateUp = navigateUp)
        }

        composable(route = WeatherDestination.route) {
            setTopBarTitle(WeatherDestination.titleRes)
            WeatherScreen(navigateUp = navigateUp)
        }
    }
}