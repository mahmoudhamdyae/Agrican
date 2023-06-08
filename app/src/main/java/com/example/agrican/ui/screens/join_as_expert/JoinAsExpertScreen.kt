package com.example.agrican.ui.screens.join_as_expert

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object JoinAsExpertDestination: NavigationDestination {
    override val route: String = "join_as_expert"
    override val titleRes: Int = R.string.join_as_expert
}

@Composable
fun JoinAsExpertScreen(
    modifier: Modifier = Modifier
) {
}