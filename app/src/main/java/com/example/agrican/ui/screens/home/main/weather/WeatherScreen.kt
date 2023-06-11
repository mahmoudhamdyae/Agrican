package com.example.agrican.ui.screens.home.main.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Weather
import com.example.agrican.ui.navigation.NavigationDestination
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
    val weather by viewModel.weather.collectAsStateWithLifecycle()

    WeatherScreenContent(weather = weather, modifier = modifier)
}

@Composable
fun WeatherScreenContent(
    weather: Weather,
    modifier: Modifier = Modifier,
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
                text = "${weather.degree}°",
                fontSize = 32.sp,
                color = greenLight
            )
        }
        Text(
            text = weather.weatherDescription,
            color = greenLight,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )

        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.air_quality, weatherData = weather.air)
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.wind, weatherData = weather.wind)
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.gusts_of_wind, weatherData = weather.windGusts)
        Divider(modifier = Modifier.fillMaxWidth())

        WeatherRow(weatherLabel = R.string.air_quality, weatherData = weather.air)
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.wind, weatherData = weather.wind)
        Divider(modifier = Modifier.fillMaxWidth())
        WeatherRow(weatherLabel = R.string.gusts_of_wind, weatherData = weather.windGusts)
        Divider(modifier = Modifier.fillMaxWidth())

        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            color = greenLight,
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = stringResource(id = R.string.air_information_button),
                color = Color.White,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small,
                )
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = weather.firstInformation)
            Text(text = weather.secondInformation)
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
    val weather = Weather(
        air = "مقبول",
        degree = 31.0,
        weatherDescription = "مشمس",
        wind = "جنوبية غربية 33 كم/س",
        windGusts = "47 كم/س",
        firstInformation = "جو مناسب لرى نبات العنب",
        secondInformation = "من المتوقع حدوث عواصف شديدة غدا"
    )
    WeatherScreenContent(weather = weather)
}