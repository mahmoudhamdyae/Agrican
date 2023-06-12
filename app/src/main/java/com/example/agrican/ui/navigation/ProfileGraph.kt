package com.example.agrican.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrican.ui.screens.home.profile.ProfileDestination
import com.example.agrican.ui.screens.home.profile.ProfileScreen
import com.example.agrican.ui.screens.home.profile.add_crop.AddCropDestination
import com.example.agrican.ui.screens.home.profile.add_crop.AddCropScreen
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmScreen
import com.example.agrican.ui.screens.home.profile.add_task.AddTaskDestination
import com.example.agrican.ui.screens.home.profile.add_task.AddTaskScreen
import com.example.agrican.ui.screens.home.profile.cost.CostDestination
import com.example.agrican.ui.screens.home.profile.cost.CostScreen
import com.example.agrican.ui.screens.home.profile.engineer_map.EngineerMapDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.EngineerMapScreen
import com.example.agrican.ui.screens.home.profile.observe_crop.ObserveCropDestination
import com.example.agrican.ui.screens.home.profile.observe_crop.ObserveCropScreen

@Composable
fun ProfileGraph(
    setTopBarTitle: (Int) -> Unit,
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

    NavHost(
        navController = navController,
        startDestination = ProfileDestination.route,
        modifier = Modifier.padding(bottom = 75.dp)
    ) {
        composable(route = ProfileDestination.route) {
            showTopBar(false)
            showBottomBar(true)
            ProfileScreen(openScreen = openScreen)
        }

        composable(route = AddFarmDestination.route) {
            showBottomBar(false)
            AddFarmScreen(navigateUp = navigateUp)
        }

        composable(route = AddCropDestination.route) {
            showBottomBar(false)
            AddCropScreen(navigateUp = navigateUp)
        }


        composable(route = ObserveCropDestination.route) {
            showBottomBar(false)
            ObserveCropScreen(navigateUp = navigateUp, openScreen = openScreen)
        }

        composable(route = AddTaskDestination.route) {
            showBottomBar(false)
            AddTaskScreen(navigateUp = navigateUp)
        }

        composable(route = EngineerMapDestination.route) {
            setTopBarTitle(EngineerMapDestination.titleRes)
            showTopBar(true)
            showBottomBar(true)
            EngineerMapScreen()
        }

        composable(route = CostDestination.route) {
            setTopBarTitle(CostDestination.titleRes)
            showTopBar(true)
            showBottomBar(true)
            CostScreen()
        }
    }
}