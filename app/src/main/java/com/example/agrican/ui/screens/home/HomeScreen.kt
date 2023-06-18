package com.example.agrican.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.AgricanServicesGraph
import com.example.agrican.ui.navigation.MainGraph
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.navigation.ProfileGraph
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.title

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
    modifier: Modifier = Modifier
) {
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val bottomNavItems = listOf(
        BottomNavItem(name = R.string.navigation_main, icon = R.drawable.main),
        BottomNavItem(name = R.string.navigation_profile, icon = R.drawable.default_image),
        BottomNavItem(name = R.string.navigation_agrican_services, icon = R.drawable.services)
    )

    var topBarTitle by rememberSaveable { mutableStateOf(R.string.navigation_agrican_services) }
    var topBarIcon by rememberSaveable { mutableStateOf(false) }
    var shouldShowTopBar by rememberSaveable { mutableStateOf(true) }
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        topBar = {
            if (shouldShowTopBar) {
                TopBar(
                    title = {
                        if (topBarIcon) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = null
                            )
                        } else {
                            Text(
                                text = stringResource(id = topBarTitle),
                                color = textGray,
                                style = MaterialTheme.typography.title
                            )
                        }
                    }
                )
            }
        },
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

        Box(modifier = modifier
            .fillMaxSize()
            .padding(
                top = contentPadding.calculateTopPadding(), bottom = MaterialTheme.spacing.default
            )
        ) {
            when (selectedItem) {
                0 -> {
                    MainGraph(
                        setTopBarTitle = { topBarTitle = it },
                        setTopBarIcon = { topBarIcon = it },
                        showTopBar = { shouldShowTopBar = it },
                        showBottomBar = { shouldShowBottomBar = it }
                    )
                }
                1 -> {
                    topBarIcon = false
                    ProfileGraph(
                        setTopBarTitle = { topBarTitle = it },
                        showTopBar = { shouldShowTopBar = it },
                        showBottomBar = { shouldShowBottomBar = it },
                    )
                }
                2 -> {
                    AgricanServicesGraph(setTopBarTitle = { topBarTitle = it })
                    shouldShowTopBar = true
                    topBarIcon = false
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreen()
}