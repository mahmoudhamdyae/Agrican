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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.navArgument
import com.example.agrican.R
import com.example.agrican.domain.model.weather.AssetParamType
import com.example.agrican.domain.model.weather.Weather
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.white

object WeatherDestination : NavigationDestination {
    override val route: String = "weather"
    override val titleRes: Int = R.string.weather
    const val weatherArg = "weather"
    val routeWithArgs = "${route}/{$weatherArg}"
    val arguments = listOf(
        navArgument(weatherArg) { type = AssetParamType() }
    )
}

@Composable
fun WeatherScreen(
    weather: Weather,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {

    BackButtonTopBar(
        title = WeatherDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        WeatherScreenContent(weather = weather)
    }
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
            .padding(horizontal = 32.dp)
            .padding(bottom = 60.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Weather Degree
            Text(
                text = "${weather.degree.toInt()}°",
                color = greenDark,
                fontSize = 62.sp,
                fontWeight = FontWeight.SemiBold,
            )

            // Weather Icon
            Icon(
                painter = painterResource(id = weather.iconRes),
                contentDescription = null,
                tint = greenDark,
                modifier = Modifier.size(64.dp)
            )
        }

        Text(
            text = stringResource(id = weather.weatherDesc),
            color = greenDark,
            style = MaterialTheme.typography.body,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)

        // Air Quality
        WeatherRow(weatherLabel = R.string.air_quality, weatherData = "مقبول")
        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)
        // Wind
        WeatherRow(
            weatherLabel = R.string.wind,
            weatherData = stringResource(id = weather.windDirection) +
                    " " +
                    stringResource(id = R.string.wind_unit, weather.wind.toString())
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)
        // Gusts of Wind
        WeatherRow(
            weatherLabel = R.string.gusts_of_wind,
            weatherData = stringResource(id = R.string.wind_unit, weather.windGusts.toString())
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)

        // Air Quality
        WeatherRow(weatherLabel = R.string.air_quality, weatherData = "مقبول")
        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)
        // Wind
        WeatherRow(
            weatherLabel = R.string.wind,
            weatherData = stringResource(id = weather.windDirection) +
                    " " +
                    stringResource(id = R.string.wind_unit, weather.wind.toString())
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)
        // Gusts of Wind
        WeatherRow(
            weatherLabel = R.string.gusts_of_wind,
            weatherData = stringResource(id = R.string.wind_unit, weather.wind.toString())
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = textGray)

        // Weather Information
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = greenLight,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.air_information_button),
                color = white,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 6.dp,
                )
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "جو مناسب لرى نبات العنب",
                style = MaterialTheme.typography.body,
                fontSize = 11.sp
            )
            Text(
                text = "من المتوقع حدوث عواصف شديدة غدا",
                style = MaterialTheme.typography.body,
                fontSize = 11.sp
            )
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
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = weatherLabel),
            style = MaterialTheme.typography.body,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = weatherData,
            style = MaterialTheme.typography.body,
            fontSize = 14.sp
        )
    }
}