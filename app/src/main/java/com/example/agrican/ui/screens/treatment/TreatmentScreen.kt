package com.example.agrican.ui.screens.treatment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object TreatmentDestination: NavigationDestination {
    override val route: String = "treatment"
    override val titleRes: Int = R.string.treatment
}

@Composable
fun TreatmentScreen(
    modifier: Modifier = Modifier
) {
}