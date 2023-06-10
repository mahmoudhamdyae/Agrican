package com.example.agrican.ui.screens.home.profile.cost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object CostDestination: NavigationDestination {
    override val route: String = "cost"
    override val titleRes: Int = R.string.cost
}

@Composable
fun CostScreen(
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun CostScreenPreview() {
    CostScreen()
}