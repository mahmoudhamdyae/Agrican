package com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theflankers.agrican.R
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.components.DropDown
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.MapScreen
import com.theflankers.agrican.ui.theme.black
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight

object AddMapDestination: NavigationDestination {
    override val route: String = "add_map"
    override val titleRes: Int = R.string.add_map
}

@Composable
fun AddMapScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddMapViewModel = hiltViewModel()
) {

    BackButtonTopBar(
        title = AddMapDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        AddMapScreenContent(openScreen = openScreen)
    }
}

@Composable
fun AddMapScreenContent(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(16.dp)
            .padding(bottom = 60.dp)
    ) {
        // Choose Farm Drop Down
        Row {
            DropDown(
                options = listOf(
                    R.string.choose_farm
                ),
                onSelect = { openScreen(AddMapTwoDestination.route) },
                textColor = greenDark,
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        // Choose Size Text
        Text(
            text = stringResource(id = R.string.choose_farm_size),
            color = greenLight,
            style = MaterialTheme.typography.body,
            fontSize = 15.sp
        )

        MapScreen(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(black)
        )

        // Continue Button
        Button(
            onClick = { openScreen(AddMapTwoDestination.route) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 64.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddMapScreenPreview() {
    AddMapScreenContent(openScreen = { })
}