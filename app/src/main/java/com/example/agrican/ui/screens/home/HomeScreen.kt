package com.example.agrican.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
}