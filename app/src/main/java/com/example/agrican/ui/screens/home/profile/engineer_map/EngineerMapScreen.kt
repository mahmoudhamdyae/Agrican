package com.example.agrican.ui.screens.home.profile.engineer_map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Farm
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.FarmsListItem
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.add_map.AddMapDestination

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
    Column(modifier = modifier) {
        Button(
            onClick = { openScreen(AddMapDestination.route) }
        ) {
            Text(text = stringResource(id = R.string.add_map))
        }

        Text(text = stringResource(id = R.string.current_maps))

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        FarmsList(farms = farms)
    }
}

@Composable
fun FarmsList(
    farms: List<Farm>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
    ) {
        repeat(farms.size) {
            item {
                FarmsListItem(
                    farm = farms[it],
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EngineerMapScreenPreview() {
    EngineerMapScreenContent(openScreen = { }, farms = listOf())
}