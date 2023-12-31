package com.theflankers.agrican.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theflankers.agrican.ui.screens.home.agricanservices.AgricanServicesDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.AgricanServicesScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.default_age.DefaultAgeScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.default_age.DefaultAgesDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.diseases.DiseaseDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.diseases.DiseaseScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.diseases.DiseasesDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.diseases.DiseasesScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.order.OrderDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.order.OrderScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.order.order_status.OrderStatusDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.order.order_status.OrderStatusScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.pests.PestDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.pests.PestScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.pests.PestsDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.pests.PestsScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.treatment.SelectedCropDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.treatment.SelectedCropScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.treatment.TreatmentDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.treatment.TreatmentScreen
import com.theflankers.agrican.ui.screens.notifications.NotificationsDestination
import com.theflankers.agrican.ui.screens.notifications.NotificationsScreen

@Composable
fun AgricanServicesGraph(
    setTopBarTitle: (Int) -> Unit,
    showBottomBar: (Boolean) -> Unit,
    navigateToLoginScreen: () -> Unit,
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
        startDestination = AgricanServicesDestination.route
    ) {

        // Agrican Services Screen
        composable(route = AgricanServicesDestination.route) {
            setTopBarTitle(AgricanServicesDestination.titleRes)
            AgricanServicesScreen(
                openScreen = openScreen,
                navigateFromSignOut = navigateFromSignOut
            )
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