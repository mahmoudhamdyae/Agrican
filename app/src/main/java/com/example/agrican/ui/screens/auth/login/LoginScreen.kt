package com.example.agrican.ui.screens.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object LoginDestination: NavigationDestination {
    override val route: String = "login"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
}