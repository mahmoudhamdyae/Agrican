package com.example.agrican.ui.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@OptIn(ExperimentalMaterial3Api::class)
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
            Box(modifier = Modifier.height(IntrinsicSize.Max)) {
                TopAppBar(
                    title = title,
                    actions = {
                        // Notifications Icon
                        Row {
                            // Notifications Icon
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
                    modifier = modifier
                        .shadow(16.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { showMenu = false }
                )

                if (showMenu) {
                    // Gray Background on Top Bar When Menu is Shown
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x63AAA6A6))
                            .clickable { showMenu = false }
                    )
                }
            }

            Box {
                content()

                // Gray Background When Menu is Shown
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
            DropDownMenuContent(
                hideMenu = { showMenu = false },
                signOutAction = { viewModel.signOut(openAndClear) },
                openNotificationsScreen = openNotificationsScreen,
                toggleLanguage = { toggleLanguage() },
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        topStart = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    ))
                    .background(white)
                    .align(Alignment.TopEnd)
            )
        }
    }

    AnimatedVisibility(isLoading) {
        DialogBoxLoading()
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