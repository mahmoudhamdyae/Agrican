package com.example.agrican.ui.screens.home.agricanservices

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.default_age.DefaultAgeScreen
import com.example.agrican.ui.screens.default_age.DefaultAgesDestination
import com.example.agrican.ui.screens.diseases.DiseasesDestination
import com.example.agrican.ui.screens.diseases.DiseasesScreen
import com.example.agrican.ui.screens.join_as_expert.JoinAsExpertDestination
import com.example.agrican.ui.screens.join_as_expert.JoinAsExpertScreen
import com.example.agrican.ui.screens.order.OrderDestination
import com.example.agrican.ui.screens.order.OrderScreen
import com.example.agrican.ui.screens.pests.PestsDestination
import com.example.agrican.ui.screens.pests.PestsScreen
import com.example.agrican.ui.screens.treatment.TreatmentDestination
import com.example.agrican.ui.screens.treatment.TreatmentScreen

@Composable
fun AgricanServicesGraph(
    setTopBarTitle: (Int) -> Unit,
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

    NavHost(navController = navController, startDestination = AgricanServicesDestination.route) {

        composable(route = AgricanServicesDestination.route) {
            AgricanServicesScreen(
                openScreen = openScreen,
                setTopBarTitle = setTopBarTitle
            )
        }

        composable(route = DefaultAgesDestination.route) {
            DefaultAgeScreen()
        }

        composable(route = OrderDestination.route) {
            OrderScreen(navigateUp = navigateUp)
        }

        composable(route = DiseasesDestination.route) {
            DiseasesScreen()
        }

        composable(route = PestsDestination.route) {
            PestsScreen()
        }

        composable(route = TreatmentDestination.route) {
            TreatmentScreen()
        }

        composable(route = JoinAsExpertDestination.route) {
            JoinAsExpertScreen()
        }
    }
}