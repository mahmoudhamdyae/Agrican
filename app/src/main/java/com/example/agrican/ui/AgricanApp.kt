package com.example.agrican.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.screens.auth.login.LoginScreen
import com.example.agrican.ui.screens.auth.signup.SignupDestination
import com.example.agrican.ui.screens.auth.signup.SignupScreen
import com.example.agrican.ui.screens.home.HomeDestination
import com.example.agrican.ui.screens.home.HomeScreen
import com.example.agrican.ui.screens.onboarding.OnboardingDestination
import com.example.agrican.ui.screens.onboarding.OnboardingScreen
import com.example.agrican.ui.screens.welcome.WelcomeDestination
import com.example.agrican.ui.screens.welcome.WelcomeScreen

@Composable
fun AgricanApp() {

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

    NavHost(navController = navController, startDestination = LoginDestination.route) {

        composable(route = WelcomeDestination.route) {
            WelcomeScreen(openAndClear = openAndClear)
        }

        composable(route = OnboardingDestination.route) {
            OnboardingScreen(openAndClear = openAndClear)
        }

        composable(route = LoginDestination.route) {
            LoginScreen(
                openScreen = openScreen,
                openAndClear = openAndClear
            )
        }

        composable(route = SignupDestination.route) {
            SignupScreen(
                openAndClear = openAndClear,
                navigateUp = navigateUp
            )
        }

        composable(route = HomeDestination.route) {
            HomeScreen()
        }
    }
}