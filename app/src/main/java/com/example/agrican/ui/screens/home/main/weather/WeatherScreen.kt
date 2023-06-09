package com.example.agrican.ui.screens.home.main.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object WeatherDestination : NavigationDestination {
    override val route: String = "weather"
    override val titleRes: Int = R.string.weather
}

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.size(MaterialTheme.spacing.extraLarge)
            )
            Text(
                text = "31°",
                fontSize = 32.sp,
                color = greenLight
            )
        }
        Text(
            text = "مشمس",
            color = greenLight,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )

        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.air_quality, weatherData = "مقبول")
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.wind, weatherData = "جنوبية غربية 33 كم/س")
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.gusts_of_wind, weatherData = "47 كم/س")
        Divider(modifier = Modifier.fillMaxWidth())

        WeatherRow(weatherLabel = R.string.air_quality, weatherData = "مقبول")
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.wind, weatherData = "جنوبية غربية 33 كم/س")
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.gusts_of_wind, weatherData = "47 كم/س")
        Divider(modifier = Modifier.fillMaxWidth())

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            Text(text = stringResource(id = R.string.air_information_button))
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "جو مناسب لرى نبات العنب")
            Text(text = "من المتوقع حدوث عواصف شديدة غدا")
        }
    }
}

@Composable
fun WeatherRow(
    weatherLabel: Int,
    weatherData: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {
        Text(text = stringResource(id = weatherLabel))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = weatherData)
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}