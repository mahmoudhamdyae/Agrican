package com.example.agrican.ui.screens.pests

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object PestsDestination: NavigationDestination {
    override val route: String = "pests"
    override val titleRes: Int = R.string.pests
}

@Composable
fun PestsScreen(
    modifier: Modifier = Modifier
) {
}