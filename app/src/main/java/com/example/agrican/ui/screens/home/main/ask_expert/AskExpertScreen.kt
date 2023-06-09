package com.example.agrican.ui.screens.home.main.ask_expert

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object AskExpertDestination: NavigationDestination {
    override val route: String = "ask_expert"
    override val titleRes: Int = R.string.ask_expert
}

@Composable
fun AskExpertScreen(
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun AskExpertScreenPreview() {
    AskExpertScreen()
}