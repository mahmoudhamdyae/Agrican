package com.example.agrican.ui.screens.home.profile.engineer_map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object EngineerMapDestination: NavigationDestination {
    override val route: String = "engineer_map"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun EngineerMapScreen(
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun EngineerMapScreenPreview() {
    EngineerMapScreen()
}