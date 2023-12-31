package com.theflankers.agrican.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.theflankers.agrican.ui.AgricanAppState
import com.theflankers.agrican.ui.screens.auth.login.LoginDestination
import com.theflankers.agrican.ui.screens.auth.login.LoginScreen
import com.theflankers.agrican.ui.screens.auth.signup.SignupDestination
import com.theflankers.agrican.ui.screens.auth.signup.SignupScreen
import com.theflankers.agrican.ui.screens.home.HomeDestination
import com.theflankers.agrican.ui.screens.home.HomeScreen
import com.theflankers.agrican.ui.screens.onboarding.OnboardingDestination
import com.theflankers.agrican.ui.screens.onboarding.OnboardingScreen
import com.theflankers.agrican.ui.screens.welcome.WelcomeDestination
import com.theflankers.agrican.ui.screens.welcome.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Provides Navigation graph for the application.
 */
@ExperimentalPagerApi
fun NavGraphBuilder.agricanAppGraph(
    appState: AgricanAppState,
) {
    val openScreen: (String) -> Unit = { route -> appState.navigate(route) }
    val openAndClear: (String) -> Unit = { route -> appState.clearAndNavigate(route) }
    val openAndPopUp: (String, String) -> Unit = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
    val navigateUp = { appState.popUp() }

    // Welcome Screen
    composable(route = WelcomeDestination.route) {
        WelcomeScreen(openAndClear = openAndClear)
    }

    // Onboarding Screen
    composable(route = OnboardingDestination.route) {
        OnboardingScreen(openAndClear = openAndClear)
    }

    // Login Screen
    composable(route = LoginDestination.route) {
        LoginScreen(openScreen = openScreen, openAndClear = openAndClear,)
    }

    // Signup Screen
    composable(route = SignupDestination.route) {
        SignupScreen(openAndClear = openAndClear, navigateUp = navigateUp)
    }

    // Home Screen
    composable(route = HomeDestination.route) {
        HomeScreen(
            navigateFromSignOut = {
                openAndClear(LoginDestination.route)
            },
            openAndClear = openAndClear
        )
    }
}