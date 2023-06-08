package com.example.agrican.ui.screens.diseases

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object DiseasesDestination: NavigationDestination {
    override val route: String = "diseases"
    override val titleRes: Int = R.string.disease
}


@Composable
fun DiseasesScreen(
    modifier: Modifier = Modifier
) {
}