package com.example.agrican.ui.screens.home.profile.engineer_map.existing_map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object ExistingMapDestination: NavigationDestination {
    override val route: String = "existing_map"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun ExistingMapScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun ExistingSMapScreenPreview() {
    ExistingMapScreen(openScreen = { })
}