package com.example.agrican.ui.screens.home.profile.engineer_map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object EngineerMapDestination: NavigationDestination {
    override val route: String = "engineer_map"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun EngineerMapScreen(
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        DropDown(options = arrayOf(
            R.string.choose_farm
        ), onSelect = { /*TODO*/ },
            modifier = Modifier.width(150.dp).height(MaterialTheme.spacing.large)
        )

        Text(
            text = stringResource(id = R.string.choose_farm_size),
            color = greenLight
        )

        // todo: Maps

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.continue_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EngineerMapScreenPreview() {
    EngineerMapScreen()
}