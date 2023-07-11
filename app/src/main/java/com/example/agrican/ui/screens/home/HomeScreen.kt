package com.example.agrican.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.BottomNavigationBar
import com.example.agrican.ui.navigation.AgricanServicesGraph
import com.example.agrican.ui.navigation.MainGraph
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.navigation.ProfileGraph
import com.example.agrican.ui.screens.auth.login.LoginDestination

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

data class BottomNavItem(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openAndClear: (String) -> Unit,
    navigateFromSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val bottomNavItems = listOf(
        BottomNavItem(name = R.string.navigation_main, icon = R.drawable.main),
        BottomNavItem(name = R.string.navigation_profile, icon = R.drawable.profile),
        BottomNavItem(name = R.string.navigation_agrican_services, icon = R.drawable.services)
    )

    var topBarTitle by rememberSaveable { mutableStateOf(R.string.navigation_agrican_services) }
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(
                    selectedItem = selectedItem,
                    setSelectedItem = { selectedItem = it },
                    bottomNavItems = bottomNavItems,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { contentPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(), bottom = 0.dp)
        ) {
            when (selectedItem) {
                0 -> {
                    MainGraph(
                        setTopBarTitle = { topBarTitle = it },
                        showBottomBar = { shouldShowBottomBar = it },
                        navigateFromSignOut = navigateFromSignOut
                    )
                }
                1 -> {
                    ProfileGraph(
                        setTopBarTitle = { topBarTitle = it },
                        showBottomBar = { shouldShowBottomBar = it },
                    )
                }
                2 -> {
                    AgricanServicesGraph(
                        setTopBarTitle = { topBarTitle = it },
                        navigateToLoginScreen = { openAndClear(LoginDestination.route) },
                        showBottomBar = { shouldShowBottomBar = it },
                        navigateFromSignOut = navigateFromSignOut
                    )
                }
            }
        }
    }
}