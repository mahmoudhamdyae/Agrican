package com.example.agrican.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.navigation.AgricanServicesGraph
import com.example.agrican.ui.navigation.MainGraph
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.navigation.ProfileGraph
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

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
        BottomNavItem(
            name = R.string.navigation_main,
            icon = R.drawable.main,
        ),
        BottomNavItem(
            name = R.string.navigation_profile,
            icon = R.drawable.default_image,
        ),
        BottomNavItem(
            name = R.string.navigation_agrican_services,
            icon = R.drawable.services,
        ),
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
                            Text(text = stringResource(id = topBarTitle))
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
                top = contentPadding.calculateTopPadding(), bottom = 0.dp
            )) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = title,
        actions = {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.notifications), contentDescription = null)
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.menu), contentDescription = null)
                }
            }
        },
        modifier = modifier.shadow(16.dp)
    )
}

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    setSelectedItem: (Int) -> Unit,
    bottomNavItems: List<BottomNavItem>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.height(110.dp)) {

        // Profile Item
        Surface(
            shape = CircleShape,
            shadowElevation = MaterialTheme.spacing.large,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            IconButton(
                onClick = { setSelectedItem(1) },
                modifier = Modifier.size(75.dp)
            ) {
                Icon(
                    painter = painterResource(id = bottomNavItems[1].icon),
                    contentDescription = null,
                    tint = if (selectedItem == 1) greenDark else gray,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
                )
            }
        }

        // Main and Services Items
        Surface(
            shape = NavigationBarCustomShape(145f),
            shadowElevation = MaterialTheme.spacing.large,
            modifier = Modifier
                .height(75.dp)
                .align(Alignment.BottomCenter)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Main Item
                    BottomNavigationItem(
                        text = bottomNavItems[0].name,
                        icon = bottomNavItems[0].icon,
                        color = if (selectedItem == 0) greenDark else gray,
                        modifier = Modifier.clickable { setSelectedItem(0) }
                    )
                    Column {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = stringResource(id = bottomNavItems[1].name),
                            color = if (selectedItem == 1) greenDark else gray,
                            modifier = Modifier
                                .clickable { setSelectedItem(1) }
                                .padding(start = MaterialTheme.spacing.large)
                        )
                    }
                    // Agrican Services Item
                    BottomNavigationItem(
                        text = bottomNavItems[2].name,
                        icon = bottomNavItems[2].icon,
                        color = if (selectedItem == 2) greenDark else gray,
                        modifier = Modifier.clickable { setSelectedItem(2) }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    text: Int,
    icon: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color
        )
        Text(text = stringResource(id = text), color = color)
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreen()
}

class NavigationBarCustomShape(private val cornerRadius: Float) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            // Draw your custom path here
            path = drawTicketPath(size = size, cornerRadius = cornerRadius)
        )
    }

    private fun drawTicketPath(size: Size, cornerRadius: Float): Path {
        return Path().apply {
            reset()
            val r2 = 16f
            val r1 = cornerRadius
            val w = size.width
            lineTo(x = w / 2 - r1 - r2, y = 0f)
            arcTo(
                rect = Rect(
                    left = w / 2 - r1 - 2 * r2,
                    top = 0f,
                    right = w / 2 - r1,
                    bottom = 2 * r2
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = 180.0f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    left = w / 2 - r1,
                    top = -r1,
                    right = w / 2 + r1,
                    bottom = r1
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = -180.0f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    left = w / 2 + r1,
                    top = 0f,
                    right = w / 2 + r1 + 2 * r2,
                    bottom = 2 * r2
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = 90.0f,
                forceMoveTo = false
            )
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f, y = 0f)
            close()
        }
    }
}