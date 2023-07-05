package com.example.agrican.ui.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.screens.home.HomeViewModel
import com.example.agrican.ui.screens.notifications.NotificationsDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.iconGray
import com.example.agrican.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TopBar(
    title: @Composable () -> Unit,
    openAndClear: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    content: @Composable () -> Unit = {},
) {
    var showMenu by remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    val openNotificationsScreen = { openScreen(NotificationsDestination.route) }

    Box {
        Column {
            TopAppBar(
                title = title,
                actions = {
                    // Notifications Icon
                    Row {
                        IconButton(onClick = openNotificationsScreen) {
                            Icon(
                                painter = painterResource(id = R.drawable.notifications),
                                contentDescription = stringResource(id = R.string.notifications),
                                tint = iconGray
                            )
                        }

                        // Menu Item
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu),
                                contentDescription = null,
                                tint = if (showMenu) greenDark else iconGray
                            )
                        }
                    }
                },
                modifier = modifier.shadow(16.dp)
            )

            Box {
                content()
                if (showMenu) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x63AAA6A6))
                            .clickable { showMenu = false }
                    )
                }
            }
        }

        if (showMenu) {
            Surface(
                modifier = Modifier
                    .background(white)
                    .align(Alignment.TopEnd)
            ) {
                DropDownMenuContent(
                    hideMenu = { showMenu = false },
                    signOutAction = { viewModel.signOut(openAndClear) },
                    openNotificationsScreen = openNotificationsScreen
                )
            }
        }
    }


    AnimatedVisibility(isLoading) {
        DialogBoxLoading()
    }
}

@Composable
fun DropDownMenuContent(
    hideMenu: () -> Unit,
    signOutAction: () -> Unit,
    openNotificationsScreen: () -> Unit,
    modifier: Modifier= Modifier
) {
    Column(modifier = modifier.width(180.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            // Settings Label
            Text(
                text = stringResource(id = R.string.settings),
                color = greenDark,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Notification Button
            IconButton(onClick = openNotificationsScreen) {
                Icon(
                    painter = painterResource(id = R.drawable.notifications),
                    contentDescription = stringResource(id = R.string.notifications),
                    tint = iconGray
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Menu Item
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                tint = greenDark
            )
        }

        Surface(
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, Color(0xFFDADADA)),
            modifier = Modifier.padding(12.dp)
        ) {
            Column(modifier = Modifier.background(white)) {
                // Language Item
                DropDownItem(
                    text = R.string.language,
                    onItemClick = {
                        toggleLanguage()
                        hideMenu()
                    }
                )

                Divider(modifier = Modifier
                    .height(2.dp)
                    .background(Color(0xFFDADADA)))

                // About Us Item
                DropDownItem(
                    text = R.string.about_us,
                    onItemClick = hideMenu
                )

                Divider(modifier = Modifier
                    .height(2.dp)
                    .background(Color(0xFFDADADA)))

                // Sign Out Item
                DropDownItem(
                    text = R.string.sign_out,
                    onItemClick = {
                        hideMenu()
                        signOutAction()
                    }
                )
            }
        }
    }
}

private fun toggleLanguage() {
    if (AppCompatDelegate.getApplicationLocales().toLanguageTags() == "en") {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ar"))
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
    }
}

@Composable
fun DropDownItem(
    text: Int,
    onItemClick: () -> Unit,
    modifier: Modifier= Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.clickable { onItemClick() }
    ) {
        // Item Icon
        Surface(
            shape = CircleShape,
            color = greenDark,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            Text(
                text = "ico",
                color = white,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(2.dp)
            )
        }
        // Item Text
        Text(
            text = stringResource(id = text),
            color = greenDark,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        openAndClear = { },
        openScreen = { }
    )
}