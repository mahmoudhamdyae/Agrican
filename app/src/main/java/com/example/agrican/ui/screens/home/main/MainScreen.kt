package com.example.agrican.ui.screens.home.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.Weather
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object MainDestination: NavigationDestination {
    override val route: String = "main"
    override val titleRes: Int = R.string.default_age_title
}

@Composable
fun MainScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreenContent(
        weather = uiState.weather,
        news = uiState.news,
        openScreen = openScreen,
        modifier = modifier
    )
}

@Composable
fun MainScreenContent(
    weather: Weather,
    news: List<News>,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        WeatherBox(
            weather = weather,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.large)
                .clickable { openScreen(WeatherDestination.route) }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            ) {
                Text(
                    text = stringResource(id = R.string.last_news),
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.last_offers),
                    color = greenDark,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }
        }

        LatestNewsList(news = news)

        BottomCard(
            title = R.string.problem_images,
            description = R.string.problem_images_description,
            icon = R.drawable.ic_visibility_on,
            onItemClick = { openScreen(ProblemImagesDestination.route) },
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth()

        )

        Row {
            BottomCard(
                title = R.string.fertilizers_calculator,
                description = R.string.fertilizers_calculator_description,
                icon = R.drawable.ic_visibility_on,
                onItemClick = { openScreen(FertilizersCalculatorDestination.route) },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
            )

            BottomCard(
                title = R.string.ask_expert,
                description = R.string.ask_expert_description,
                icon = R.drawable.ic_visibility_on,
                onItemClick = { openScreen(AskExpertDestination.route) },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
            )
        }
    }
}

@Composable
fun WeatherBox(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(1.dp, Color.Gray),
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Row(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
            Column {
                Text(
                    text = "الطقس",
                    color = greenDark
                )
                Text(text = "جودة الهواء")
                Text(text = "الرياح")
                Text(text = "هبات الرياح")
            }
            Column(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            ) {
                Text(text = "")
                Text(text = weather.air)
                Text(text = weather.wind)
                Text(text = weather.windGusts)
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
            ) {
                Text(text = weather.firstInformation)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "مشمس",
                        color = greenDark,
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    )
                    Text(
                        text = "31°",
                        color = greenDark,
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sunny),
                        contentDescription = null,
                        tint = greenDark,
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        }
    }
}

@Composable
fun LatestNewsList(
    news: List<News>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .height(85.dp)
                .background(greenDark)
        )
        LazyRow {
            items(news.size) {
                LatestNewsListItem(
                    news = news[it],
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .height(100.dp)
                        .width(150.dp)
                )
            }
        }

        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = CircleShape,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .align(Alignment.CenterStart)
                .clickable {
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )
        }

        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = CircleShape,
            modifier = Modifier
                .padding(end = MaterialTheme.spacing.medium)
                .align(Alignment.CenterEnd)
                .clickable {
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )
        }
    }
}

@Composable
fun LatestNewsListItem(
    news: News,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_visibility_on),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )
            Text(
                text = news.title,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

@Composable
fun BottomCard(
    title: Int,
    description: Int,
    icon: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    body: @Composable () -> Unit = { }
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        shadowElevation = MaterialTheme.spacing.medium,
        modifier = modifier.clickable { onItemClick() }
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Row {
                Text(
                    text = stringResource(id = title),
                    color = greenDark
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = greenDark
                )
            }
            Text(text = stringResource(id = description))
            body()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    val weather = Weather(
        air = "مقبول",
        degree = 31.0,
        weatherDescription = "مشمس",
        wind = "جنوبية غربية 33 كم/س",
        windGusts = "47 كم/س",
        firstInformation = "جو مناسب لرى نبات العنب",
        secondInformation = "من المتوقع حدوث عواصف شديدة غدا"
    )
    val news = listOf(
        News(title = "ابتكار طرق جديدة"),
        News(title = "ابتكار طرق جديدة"),
        News(title = "ابتكار طرق جديدة"),
        News(title = "ابتكار طرق جديدة"),
        News(title = "ابتكار طرق جديدة"),
    )
    MainScreenContent(weather = weather, news = news, openScreen = { })
}