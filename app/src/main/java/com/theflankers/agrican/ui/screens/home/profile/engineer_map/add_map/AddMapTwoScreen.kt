package com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.MapScreen
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.existing_map.ExistingMapDestination
import com.theflankers.agrican.ui.theme.black
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight

object AddMapTwoDestination: NavigationDestination {
    override val route: String = "add_map_two"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun AddMapTwoScreen(
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
        AddMapTwoScreenContent(openScreen = openScreen)
    }
}

@Composable
fun AddMapTwoScreenContent(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(16.dp)
            .padding(bottom = 60.dp)
    ) {

        Text(
            text = stringResource(id = R.string.engineer_map_problem),
            color = greenLight,
            modifier = Modifier.padding(top = 8.dp)
        )

        MapScreen(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .weight(1f)
                .background(black)
        )

        // Continue Button
        Button(
            onClick = { openScreen(ExistingMapDestination.route) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 36.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddMapTwoScreenPreview() {
    AddMapTwoScreenContent(openScreen = { })
}