package com.example.agrican.ui.screens.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.iconGray
import com.example.agrican.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: @Composable () -> Unit,
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = title,
        actions = {
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {

                // Language Item
                DropDownItem(
                    text = R.string.language,
                    onItemClick = {
                        toggleLanguage()
                        showMenu = false
                    }
                )

                // About Us Item
                DropDownItem(
                    text = R.string.about_us,
                    onItemClick = { showMenu = false }
                )
                
                // Sign Out Item
                DropDownItem(
                    text = R.string.sign_out,
                    onItemClick = {
                        showMenu = false
                        viewModel.signOut(openAndClear)
                    }
                )
            }

            // Notifications Icon
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notifications),
                        contentDescription = null,
                        tint = iconGray
                    )
                }

                // Menu Item
                IconButton(onClick = { showMenu = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = null,
                        tint = iconGray
                    )
                }
            }
        },
        modifier = modifier.shadow(16.dp)
    )
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
    DropdownMenuItem(
        modifier = modifier,
        onClick = onItemClick,
        colors = MenuDefaults.itemColors(),
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Language Icon
                Surface(shape = CircleShape, color = greenDark) {
                    Text(
                        text = "ico",
                        color = white,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                // Language Text
                Text(
                    text = stringResource(id = text),
                    color = greenDark,
                    fontSize = 14.sp
                )
            }
        }
    )
}