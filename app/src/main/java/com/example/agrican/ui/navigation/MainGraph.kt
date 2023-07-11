@file:Suppress("DEPRECATION")

package com.example.agrican.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.domain.model.weather.Weather
import com.example.agrican.ui.screens.home.main.MainDestination
import com.example.agrican.ui.screens.home.main.MainScreen
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertScreen
import com.example.agrican.ui.screens.home.main.ask_expert.ChatDestination
import com.example.agrican.ui.screens.home.main.ask_expert.ChatScreen
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorResultDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorResultScreen
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorScreen
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesScreen
import com.example.agrican.ui.screens.home.main.problem_images.disease_capture_result.DiseaseCaptureResultDestination
import com.example.agrican.ui.screens.home.main.problem_images.disease_capture_result.DiseaseCaptureResultScreen
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherScreen
import com.example.agrican.ui.screens.notifications.NotificationsDestination
import com.example.agrican.ui.screens.notifications.NotificationsScreen

@Composable
fun MainGraph(
    setTopBarTitle: (Int) -> Unit,
    showBottomBar: (Boolean) -> Unit,
    navigateFromSignOut: () -> Unit,
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
        // Main Screen
        composable(route = MainDestination.route) {
            showBottomBar(true)
            MainScreen(
                openScreen = openScreen,
                navigateFromSignOut = navigateFromSignOut
            )
        }

        // Problem Images Screen
        composable(route = ProblemImagesDestination.route) {
            showBottomBar(false)
            ProblemImagesScreen(
                navigateUp = navigateUp,
                openCamera = { showBottomBar(false) },
                openScreen = openScreen
            )
        }

        // Disease Capture Result Screen
        composable(route = DiseaseCaptureResultDestination.route) {
            showBottomBar(false)
            DiseaseCaptureResultScreen(navigateUp = navigateUp, openAndClear = openAndClear)
        }

        // Fertilizers Calculator Screen
        composable(route = FertilizersCalculatorDestination.route) {
            setTopBarTitle(FertilizersCalculatorDestination.titleRes)
            FertilizersCalculatorScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Fertilizers Calculator Result Screen
        composable(route = FertilizersCalculatorResultDestination.route) {
            setTopBarTitle(FertilizersCalculatorResultDestination.titleRes)
            FertilizersCalculatorResultScreen(navigateUp = navigateUp, openAndClear = openAndClear)
        }

        // Ask An Expert Screen
        composable(route = AskExpertDestination.route) {
            showBottomBar(false)
            AskExpertScreen(openAndPopUp = openAndPopUp)
        }

        // Chat Screen
        composable(route = ChatDestination.route) {
            ChatScreen(navigateUp = navigateUp)
        }

        // Weather Screen
        composable(
            route = WeatherDestination.routeWithArgs,
            arguments = WeatherDestination.arguments
        ) {
            val weather = it.arguments?.getParcelable<Weather>(WeatherDestination.weatherArg)!!

            showBottomBar(false)
            setTopBarTitle(WeatherDestination.titleRes)
            WeatherScreen(weather = weather, navigateUp = navigateUp)
        }

        // Notifications Screen
        composable(route = NotificationsDestination.route) {
            NotificationsScreen(navigateUp = navigateUp)
        }
    }
}