package com.theflankers.agrican.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theflankers.agrican.ui.screens.home.profile.ProfileDestination
import com.theflankers.agrican.ui.screens.home.profile.ProfileScreen
import com.theflankers.agrican.ui.screens.home.profile.add_crop.AddCropDestination
import com.theflankers.agrican.ui.screens.home.profile.add_crop.AddCropScreen
import com.theflankers.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.theflankers.agrican.ui.screens.home.profile.add_farm.AddFarmScreen
import com.theflankers.agrican.ui.screens.home.profile.add_task.AddTaskDestination
import com.theflankers.agrican.ui.screens.home.profile.add_task.AddTaskScreen
import com.theflankers.agrican.ui.screens.home.profile.cost.CostDestination
import com.theflankers.agrican.ui.screens.home.profile.cost.CostScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.EngineerMapDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.EngineerMapScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map.AddMapDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map.AddMapScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map.AddMapTwoDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map.AddMapTwoScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_problem.AddProblemDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_problem.AddProblemScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_progress.AddProgressDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_progress.AddProgressScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.existing_map.ExistingMapDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.existing_map.ExistingMapScreen
import com.theflankers.agrican.ui.screens.home.profile.observe_crop.ObserveCropDestination
import com.theflankers.agrican.ui.screens.home.profile.observe_crop.ObserveCropScreen

@Composable
fun ProfileGraph(
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
        startDestination = ProfileDestination.route
    ) {

        // Profile Screen
        composable(route = ProfileDestination.route) {
            showBottomBar(true)
            ProfileScreen(openScreen = openScreen)
        }

        // Add Farm Screen
        composable(route = AddFarmDestination.route) {
            showBottomBar(false)
            AddFarmScreen(navigateUp = navigateUp)
        }

        // Add Crop Screen
        composable(route = AddCropDestination.route) {
            showBottomBar(false)
            AddCropScreen(navigateUp = navigateUp)
        }

        // Observe Crop Screen
        composable(
            route = ObserveCropDestination.route,
//            arguments = ObserveCropDestination.arguments
        ) {
            showBottomBar(false)
            ObserveCropScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Add Task Screen
        composable(route = AddTaskDestination.route) {
            showBottomBar(false)
            AddTaskScreen(navigateUp = navigateUp)
        }

        // Engineer Map Screen
        composable(route = EngineerMapDestination.route) {
            showBottomBar(true)
            setTopBarTitle(EngineerMapDestination.titleRes)
            EngineerMapScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Add Map Screen
        composable(route = AddMapDestination.route) {
            showBottomBar(false)
            setTopBarTitle(AddMapDestination.titleRes)
            AddMapScreen(
                navigateUp = navigateUp,
                openScreen = openScreen
            )
        }

        // Second Add Map Screen
        composable(route = AddMapTwoDestination.route) {
            showBottomBar(false)
            setTopBarTitle(AddMapTwoDestination.titleRes)
            AddMapTwoScreen(
                navigateUp = navigateUp,
                openScreen = openScreen
            )
        }

        // Existing Map Screen
        composable(route = ExistingMapDestination.route) {
            showBottomBar(true)
            setTopBarTitle(ExistingMapDestination.titleRes)
            ExistingMapScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        // Add Progress Screen
        composable(route = AddProgressDestination.route) {
            showBottomBar(true)
            setTopBarTitle(AddProgressDestination.titleRes)
            AddProgressScreen(navigateUp = navigateUp)
        }

        // Add Problem Screen
        composable(route = AddProblemDestination.route) {
            showBottomBar(false)
            setTopBarTitle(AddProblemDestination.titleRes)
            AddProblemScreen(navigateUp = navigateUp)
        }

        // Cost Screen
        composable(route = CostDestination.route) {
            showBottomBar(true)
            setTopBarTitle(CostDestination.titleRes)
            CostScreen(navigateUp = navigateUp)
        }
    }
}