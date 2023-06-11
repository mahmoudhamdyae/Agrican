package com.example.agrican.ui.screens.home.profile.add_crop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object AddCropDestination: NavigationDestination {
    override val route: String = "add_crop"
    override val titleRes: Int = R.string.add_crop
}

@Composable
fun AddCropScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddCropViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileHeader(navigateUp = navigateUp, headerText = AddCropDestination.titleRes) {
        AddCropScreenContent(uiState = uiState, addCrop = viewModel::addCrop, modifier = modifier)
    }
}

@Composable
fun AddCropScreenContent(
    uiState: AddCropUiState,
    addCrop: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.choose_crop_label),
            color = greenDark,
            modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
        )

        CropsList(crops = uiState.crops, setSelectedCrop = { uiState.selectedCrop = it })

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            val context = LocalContext.current
            Text(
                text = stringResource(id = R.string.agri_history),
                color = greenDark
            )
            DropDown(availabilityOptions = arrayOf(
                R.string.day
            ),
                onSelect = { uiState.day = context.getString(it) },
                modifier = Modifier.weight(1f)
            )
            DropDown(availabilityOptions = arrayOf(
                R.string.month
            ),
                onSelect = { uiState.month = context.getString(it) },
                modifier = Modifier.weight(1f)
            )
            DropDown(availabilityOptions = arrayOf(
                R.string.year
            ),
                onSelect = { uiState.year = context.getString(it) },
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = addCrop,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.add_crop_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCropScreenPreview() {
    AddCropScreenContent(uiState = AddCropUiState(), addCrop = { })
}