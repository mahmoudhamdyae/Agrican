package com.example.agrican.ui

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.agrican.common.snackbar.SnackBarManager
import com.example.agrican.ui.navigation.agricanAppGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope

@Composable
fun AgricanApp(startDestination: String) {

    val snackBarHostState = remember { SnackbarHostState() }
    val appState = rememberAppState(snackBarHostState = snackBarHostState)

    AgricanAppContent(
        snackBarHostState = snackBarHostState,
        appState = appState,
        startDestination = startDestination
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
private fun AgricanAppContent(
    snackBarHostState: SnackbarHostState,
    appState: AgricanAppState,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackBarData ->
                    Snackbar(snackBarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = appState.navController,
            startDestination = startDestination,
            modifier = modifier.padding(innerPadding)
        ) {
            agricanAppGraph(
                appState = appState,
            )
        }
    }
}

@Composable
fun rememberAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) =
    remember(snackBarHostState, navController, snackBarManager, resources, coroutineScope) {
        AgricanAppState(snackBarHostState, navController, snackBarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}