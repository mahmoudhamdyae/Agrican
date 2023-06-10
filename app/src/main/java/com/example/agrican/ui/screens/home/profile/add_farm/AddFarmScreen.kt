package com.example.agrican.ui.screens.home.profile.add_farm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object AddFarmDestination: NavigationDestination {
    override val route: String = "add_farm"
    override val titleRes: Int = R.string.add_farm
}

@Composable
fun AddFarmScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun AddFarmScreenPreview() {
    AddFarmScreen(navigateUp = { })
}