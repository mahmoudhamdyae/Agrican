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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object EngineerMapDestination: NavigationDestination {
    override val route: String = "engineer_map"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun EngineerMapScreen(
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(16.dp)
            .padding(bottom = 60.dp)
    ) {
        // Choose Farm Drop Down
        DropDown(
            options = listOf(
                R.string.choose_farm),
            onSelect = { /*TODO*/ },
            textColor = greenDark,
            modifier = Modifier
                .width(150.dp)
                .height(32.dp)
        )

        // Choose Size Text
        Text(
            text = stringResource(id = R.string.choose_farm_size),
            color = greenLight,
            style = MaterialTheme.typography.body,
            fontSize = 15.sp
        )

        MapScreen(modifier = Modifier.fillMaxWidth().weight(1f).background(black))

        // Continue Button
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(bottom = 32.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = 15.sp,
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