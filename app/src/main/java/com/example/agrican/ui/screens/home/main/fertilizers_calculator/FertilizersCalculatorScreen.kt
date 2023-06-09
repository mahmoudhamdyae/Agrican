package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object FertilizersCalculatorDestination: NavigationDestination {
    override val route: String = "fertilizers_calculator"
    override val titleRes: Int = R.string.fertilizers_calculator
}

@Composable
fun FertilizersCalculatorScreen(
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun FertilizersCalculatorScreenPreview() {
    FertilizersCalculatorScreen()
}