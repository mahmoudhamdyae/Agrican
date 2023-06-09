package com.example.agrican.ui.screens.home.agricanservices

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.home.agricanservices.default_age.DefaultAgeScreen
import com.example.agrican.ui.screens.home.agricanservices.default_age.DefaultAgesDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseaseDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseaseScreen
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseasesDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseasesScreen
import com.example.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertDestination
import com.example.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertScreen
import com.example.agrican.ui.screens.home.agricanservices.order.OrderConfirmDestination
import com.example.agrican.ui.screens.home.agricanservices.order.OrderConfirmScreen
import com.example.agrican.ui.screens.home.agricanservices.order.OrderDestination
import com.example.agrican.ui.screens.home.agricanservices.order.OrderScreen
import com.example.agrican.ui.screens.home.agricanservices.order.OrderStatusDestination
import com.example.agrican.ui.screens.home.agricanservices.order.OrderStatusScreen
import com.example.agrican.ui.screens.home.agricanservices.pests.PestDestination
import com.example.agrican.ui.screens.home.agricanservices.pests.PestScreen
import com.example.agrican.ui.screens.home.agricanservices.pests.PestsDestination
import com.example.agrican.ui.screens.home.agricanservices.pests.PestsScreen
import com.example.agrican.ui.screens.home.agricanservices.treatment.SelectedCropDestination
import com.example.agrican.ui.screens.home.agricanservices.treatment.SelectedCropScreen
import com.example.agrican.ui.screens.home.agricanservices.treatment.TreatmentDestination
import com.example.agrican.ui.screens.home.agricanservices.treatment.TreatmentScreen

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

    NavHost(
        navController = navController,
        startDestination = AgricanServicesDestination.route,
        modifier = Modifier.padding(bottom = 75.dp)
    ) {

        composable(route = AgricanServicesDestination.route) {
            AgricanServicesScreen(openScreen = openScreen)
            setTopBarTitle(AgricanServicesDestination.titleRes)
        }

        composable(route = DefaultAgesDestination.route) {
            DefaultAgeScreen()
            setTopBarTitle(DefaultAgesDestination.titleRes)
        }

        composable(route = OrderDestination.route) {
            OrderScreen(
                openScreen = openScreen,
                navigateUp = navigateUp
            )
            setTopBarTitle(OrderDestination.titleRes)
        }

        composable(route = OrderStatusDestination.route) {
            OrderStatusScreen(navigateUp = navigateUp)
        }

        composable(route = OrderConfirmDestination.route) {
            OrderConfirmScreen(navigateUp = navigateUp)
        }

        composable(route = DiseasesDestination.route) {
            DiseasesScreen(navigateUp = navigateUp, openScreen = openScreen)
            setTopBarTitle(DiseasesDestination.titleRes)
        }

        composable(route = DiseaseDestination.route) {
            DiseaseScreen(navigateUp = navigateUp)
        }

        composable(route = PestsDestination.route) {
            PestsScreen(navigateUp = navigateUp, openScreen = openScreen)
            setTopBarTitle(PestsDestination.titleRes)
        }

        composable(route = PestDestination.route) {
            PestScreen(navigateUp = navigateUp)
        }

        composable(route = TreatmentDestination.route) {
            TreatmentScreen(navigateUp = navigateUp, openScreen = openScreen)
            setTopBarTitle(TreatmentDestination.titleRes)
        }

        composable(route = SelectedCropDestination.route) {
            SelectedCropScreen()
        }

        composable(route = JoinAsExpertDestination.route) {
            JoinAsExpertScreen()
            setTopBarTitle(JoinAsExpertDestination.titleRes)
        }
    }
}