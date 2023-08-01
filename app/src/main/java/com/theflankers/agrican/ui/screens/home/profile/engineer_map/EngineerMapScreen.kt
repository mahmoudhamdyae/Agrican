package com.theflankers.agrican.ui.screens.home.profile.engineer_map

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.Farm
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.components.FarmsListItem
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map.AddMapDestination
import com.theflankers.agrican.ui.screens.home.profile.engineer_map.existing_map.ExistingMapDestination
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.white

object EngineerMapDestination: NavigationDestination {
    override val route: String = "engineer_map"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun EngineerMapScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EngineerMapViewModel = hiltViewModel()
) {
    val farms by viewModel.farms.collectAsStateWithLifecycle()

    BackButtonTopBar(
        title = EngineerMapDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        EngineerMapScreenContent(
            farms = farms,
            openScreen = openScreen
        )
    }
}

@Composable
fun EngineerMapScreenContent(
    farms: List<Farm>,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Button(
            onClick = { openScreen(AddMapDestination.route) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.add_map),
                color = white,
                modifier = Modifier.padding(horizontal = 64.dp, vertical = 4.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.current_maps),
            color = greenDark
        )

        Divider()

        FarmsList(farms = farms, openScreen = openScreen)
    }
}

@Composable
fun FarmsList(
    farms: List<Farm>,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
    ) {
        items(items = farms, key = { it.farmId}) {
            FarmsListItem(
                farm = it,
                modifier = Modifier.clickable { openScreen(ExistingMapDestination.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EngineerMapScreenPreview() {
    EngineerMapScreenContent(
        openScreen = { },
        farms = listOf(
            Farm(name = "المزرعة الأولى"),
            Farm(name = "المزرعة الثانية"),
            Farm(name = "المزرعة الثالثة"),
            Farm(name = "المزرعة الرابعة"),
            Farm(name = "المزرعة الخامسة"),
            Farm(name = "المزرعة السادسة"),
            Farm(name = "المزرعة السابعة"),
        )
    )
}