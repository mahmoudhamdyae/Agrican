package com.example.agrican.ui.screens.home.profile.add_farm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.LabelItem
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object AddFarmDestination: NavigationDestination {
    override val route: String = "add_farm"
    override val titleRes: Int = R.string.add_farm
}

@Composable
fun AddFarmScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddFarmViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileHeader(navigateUp = navigateUp, headerText = AddFarmDestination.titleRes) {
        AddFarmScreenContent(uiState = uiState, addFarm = viewModel::addFarm, modifier = modifier)
    }
}

@Composable
fun AddFarmScreenContent(
    uiState: AddFarmUiState,
    addFarm: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        LabelWithTextField(
            value = uiState.farmName,
            onNewValue = { uiState.farmName = it },
            hint = R.string.farm_name,
            label = R.string.farm_name,
            focusManager = focusManager
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelWithTextField(
                value = uiState.farmSize,
                onNewValue = { uiState.farmSize = it },
                hint = R.string.size,
                label = R.string.size,
                focusManager = focusManager,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DropDown(options = arrayOf(
                R.string.square_kilometer
            ),
                onSelect = { uiState.sizeUnit = context.getString(it) },
                modifier = Modifier.width(130.dp).fillMaxHeight()
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.harvest_season)

            DropDown(options = arrayOf(
                R.string.day
            ),
                onSelect = { uiState.day = context.getString(it) },
                modifier = Modifier.weight(1f).fillMaxHeight()
            )
            DropDown(options = arrayOf(
                R.string.month
            ),
                onSelect = { uiState.month = context.getString(it) },
                modifier = Modifier.weight(1f).fillMaxHeight()
            )
            DropDown(options = arrayOf(
                R.string.year
            ),
                onSelect = { uiState.year = context.getString(it) },
                modifier = Modifier.weight(1f).fillMaxHeight()
            )
        }

        LabelWithTextField(
            value = uiState.cropsType,
            onNewValue = { uiState.cropsType = it },
            hint = R.string.crops_type,
            label = R.string.crops_type,
            focusManager = focusManager,
            imeAction = ImeAction.Done
        )

        Button(
            onClick = { addFarm() },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(text = stringResource(id = R.string.add_farm))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddFarmScreenPreview() {
    AddFarmScreenContent(uiState = AddFarmUiState(), addFarm = { })
}