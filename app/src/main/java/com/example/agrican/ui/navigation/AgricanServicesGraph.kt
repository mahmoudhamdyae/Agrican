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

        composable(route = AgricanServicesDestination.route) {
            setTopBarTitle(AgricanServicesDestination.titleRes)
            AgricanServicesScreen(openScreen = openScreen, openAndClear = openAndClear)
        }

        composable(route = DefaultAgesDestination.route) {
            DefaultAgeScreen(navigateUp = navigateUp)
        }

        composable(route = OrderDestination.route) {
            showBottomBar(true)
            OrderScreen(openScreen = openScreen, navigateUp = navigateUp)
        }

        composable(route = OrderStatusDestination.route) {
            showBottomBar(false)
            OrderStatusScreen(navigateUp = navigateUp, navigateToLoginScreen = navigateToLoginScreen)
        }

        composable(route = DiseasesDestination.route) {
            DiseasesScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        composable(
            route = DiseaseDestination.routeWithArgs,
            arguments = DiseaseDestination.arguments
        ) {
            DiseaseScreen(navigateUp = navigateUp)
        }

        composable(route = PestsDestination.route) {
            PestsScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        composable(
            route = PestDestination.routeWithArgs,
            arguments = PestDestination.arguments
        ) {
            PestScreen(navigateUp = navigateUp)
        }

        composable(route = TreatmentDestination.route) {
            TreatmentScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        composable(route = SelectedCropDestination.route) {
            SelectedCropScreen(navigateUp = navigateUp)
        }

        composable(route = JoinAsExpertDestination.route) {
            JoinAsExpertScreen(navigateUp = navigateUp)
        }

        composable(route = NotificationsDestination.route) {
            NotificationsScreen(navigateUp = navigateUp)
        }
    }
}