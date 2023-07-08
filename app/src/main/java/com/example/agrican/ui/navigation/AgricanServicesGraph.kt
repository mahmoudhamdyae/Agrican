package com.example.agrican.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.home.agricanservices.AgricanServicesDestination
import com.example.agrican.ui.screens.home.agricanservices.AgricanServicesScreen
import com.example.agrican.ui.screens.home.agricanservices.default_age.DefaultAgeScreen
import com.example.agrican.ui.screens.home.agricanservices.default_age.DefaultAgesDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseaseDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseaseScreen
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseasesDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseasesScreen
import com.example.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertDestination
import com.example.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertScreen
import com.example.agrican.ui.screens.home.agricanservices.order.OrderDestination
import com.example.agrican.ui.screens.home.agricanservices.order.OrderScreen
import com.example.agrican.ui.screens.home.agricanservices.order.order_status.OrderStatusDestination
import com.example.agrican.ui.screens.home.agricanservices.order.order_status.OrderStatusScreen
import com.example.agrican.ui.screens.home.agricanservices.pests.PestDestination
import com.example.agrican.ui.screens.home.agricanservices.pests.PestScreen
import com.example.agrican.ui.screens.home.agricanservices.pests.PestsDestination
import com.example.agrican.ui.screens.home.agricanservices.pests.PestsScreen
import com.example.agrican.ui.screens.home.agricanservices.treatment.SelectedCropDestination
import com.example.agrican.ui.screens.home.agricanservices.treatment.SelectedCropScreen
import com.example.agrican.ui.screens.home.agricanservices.treatment.TreatmentDestination
import com.example.agrican.ui.screens.home.agricanservices.treatment.TreatmentScreen
import com.example.agrican.ui.screens.notifications.NotificationsDestination
import com.example.agrican.ui.screens.notifications.NotificationsScreen

@Composable
fun AgricanServicesGraph(
    setTopBarTitle: (Int) -> Unit,
    showBottomBar: (Boolean) -> Unit,
    navigateToLoginScreen: () -> Unit,
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
        startDestination = AgricanServicesDestination.route
    ) {

        // Agrican Services Screen
        composable(route = AgricanServicesDestination.route) {
            setTopBarTitle(AgricanServicesDestination.titleRes)
            AgricanServicesScreen(openScreen = openScreen, openAndClear = openAndClear)
        }

        // Default Age Screen
        composable(route = DefaultAgesDestination.route) {
            DefaultAgeScreen(navigateUp = navigateUp)
        }

        // Order Screen
        composable(route = OrderDestination.route) {
            showBottomBar(true)
            OrderScreen(openScreen = openScreen, navigateUp = navigateUp)
        }

        // Order Status Screen
        composable(route = OrderStatusDestination.route) {
            showBottomBar(false)
            OrderStatusScreen(navigateUp = navigateUp, navigateToLoginScreen = navigateToLoginScreen)
        }

        // Diseases Screen
        composable(route = DiseasesDestination.route) {
            showBottomBar(true)
            DiseasesScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Disease Screen
        composable(
            route = DiseaseDestination.routeWithArgs,
            arguments = DiseaseDestination.arguments
        ) {
            showBottomBar(false)
            DiseaseScreen(navigateUp = navigateUp)
        }

        // Pests Screen
        composable(route = PestsDestination.route) {
            showBottomBar(true)
            PestsScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Pest Screen
        composable(
            route = PestDestination.routeWithArgs,
            arguments = PestDestination.arguments
        ) {
            showBottomBar(false)
            PestScreen(navigateUp = navigateUp)
        }

        // Treatment Screen
        composable(route = TreatmentDestination.route) {
            TreatmentScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Selected Crop Screen
        composable(route = SelectedCropDestination.route) {
            SelectedCropScreen(navigateUp = navigateUp)
        }

        // Join As Expert Screen
        composable(route = JoinAsExpertDestination.route) {
            JoinAsExpertScreen(navigateUp = navigateUp)
        }

        // Notifications Screen
        composable(route = NotificationsDestination.route) {
            NotificationsScreen(navigateUp = navigateUp)
        }
    }
}