package com.example.agrican.ui.screens.home.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertScreen
import com.example.agrican.ui.screens.home.main.ask_expert.ChatDestination
import com.example.agrican.ui.screens.home.main.ask_expert.ChatScreen
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorScreen
import com.example.agrican.ui.screens.home.main.problem_images.CameraDestination
import com.example.agrican.ui.screens.home.main.problem_images.CameraScreen
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

    var shouldShowBottomPadding by rememberSaveable { mutableStateOf(true) }

    NavHost(
        navController = navController,
        startDestination = MainDestination.route,
        modifier = Modifier.padding(bottom = if (shouldShowBottomPadding) 75.dp else 0.dp)
    ) {
        composable(route = MainDestination.route) {
            MainScreen(openScreen = openScreen)
            setTopBarIcon(true)
        }

        composable(route = ProblemImagesDestination.route) {
            showTopBar(true)
            showBottomBar(true)
            shouldShowBottomPadding = true
            ProblemImagesScreen(navigateUp = navigateUp, openScreen = openScreen)
            setTopBarIcon(false)
            setTopBarTitle(ProblemImagesDestination.titleRes)
        }

        composable(route = CameraDestination.route) {
            showTopBar(false)
            showBottomBar(false)
            shouldShowBottomPadding = false
            CameraScreen(navigateUp = navigateUp)
        }

        composable(route = FertilizersCalculatorDestination.route) {
            FertilizersCalculatorScreen()
            setTopBarIcon(false)
            setTopBarTitle(FertilizersCalculatorDestination.titleRes)
        }

        composable(route = AskExpertDestination.route) {
            AskExpertScreen(openAndPopUp = openAndPopUp, navigateUp = navigateUp)
            setTopBarIcon(false)
            setTopBarTitle(AskExpertDestination.titleRes)
        }

        composable(route = ChatDestination.route) {
            ChatScreen(navigateUp = navigateUp)
            setTopBarIcon(false)
            setTopBarTitle(ChatDestination.titleRes)
        }

        composable(route = WeatherDestination.route) {
            WeatherScreen()
            setTopBarIcon(false)
            setTopBarTitle(WeatherDestination.titleRes)
        }
    }
}