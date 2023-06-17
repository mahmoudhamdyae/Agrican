package com.example.agrican.ui.screens.home.profile.engineer_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.body
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
        // Choose Farm Drop Down
        DropDown(
            options = listOf(
                R.string.choose_farm),
            onSelect = { /*TODO*/ },
            textColor = greenDark,
            modifier = Modifier
                .width(MaterialTheme.spacing.dp_150)
                .height(MaterialTheme.spacing.large)
        )

        // Choose Size Text
        Text(
            text = stringResource(id = R.string.choose_farm_size),
            color = greenLight,
            style = MaterialTheme.typography.body,
            fontSize = MaterialTheme.spacing.sp_15
        )

        MapScreen(modifier = Modifier.fillMaxWidth().weight(1f).background(black))

        // Continue Button
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.large).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = MaterialTheme.spacing.sp_15,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EngineerMapScreenPreview() {
    EngineerMapScreen()
}