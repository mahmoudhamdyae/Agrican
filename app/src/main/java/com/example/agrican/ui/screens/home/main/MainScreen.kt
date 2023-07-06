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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.weather.Weather
import com.example.agrican.domain.model.weather.WeatherInfo
import com.example.agrican.domain.model.weather.toJson
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.components.TopBar
import com.example.agrican.ui.components.shimmerEffect
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white
import kotlinx.coroutines.launch

object MainDestination: NavigationDestination {
    override val route: String = "main"
    override val titleRes: Int = R.string.default_age_title
}

@Composable
fun MainScreen(
    openScreen: (String) -> Unit,
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TopBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.agrican_logo),
                contentDescription = null
            )
        },
        openAndClear = openAndClear,
        openScreen = openScreen
    ) {
        MainScreenContent(
            uiState = uiState,
            openScreen = openScreen,
            modifier = modifier
        )
    }
}

@Composable
fun MainScreenContent(
    uiState: MainUiState,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isNews by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        WeatherBoxLoading(
            uiState = uiState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .clickable {
                    val currentWeatherData = uiState.weather?.currentWeatherData

                    val weather = Weather(
                        degree = currentWeatherData!!.temperatureCelsius,
                        wind = currentWeatherData.windSpeed,
                        weatherDesc = currentWeatherData.weatherType.weatherDesc,
                        iconRes = currentWeatherData.weatherType.iconRes
                    )

                    openScreen("${WeatherDestination.route}/${weather.toJson()}")
                }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            // Latest News Button
            Button(
                onClick = { isNews = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNews) greenDark else white
                ),
                border = BorderStroke(1.dp, if (isNews) greenDark else gray),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.last_news),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isNews) white else greenDark
                )
            }

            // Latest Offers Button
            Button(
                onClick = { isNews = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNews) white else greenDark
                ),
                border = BorderStroke(1.dp, if (isNews) gray else greenDark),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.last_offers),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isNews) greenDark else white,
                )
            }
        }

        LatestNewsList(
            uiState = uiState,
            isNews = isNews,
            modifier = Modifier.padding(top = 12.dp)
        )

        // Problem Images Card
        BottomCard(
            title = R.string.problem_images,
            description = R.string.problem_images_description,
            icon = R.drawable.camera,
            onItemClick = { openScreen(ProblemImagesDestination.route) },
            body = { ProblemImagesRow() },
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .fillMaxWidth()

        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .height(130.dp)
        ) {
            // Fertilizers Calculator Card
            BottomCard(
                title = R.string.fertilizers_calculator,
                description = R.string.fertilizers_calculator_description,
                icon = R.drawable.calculator,
                onItemClick = { openScreen(FertilizersCalculatorDestination.route) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            // Ask An Expert Card
            BottomCard(
                title = R.string.ask_expert,
                description = R.string.ask_expert_description,
                icon = R.drawable.ask_expert,
                onItemClick = { openScreen(AskExpertDestination.route) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun WeatherBoxLoading(
    uiState: MainUiState,
    modifier: Modifier = Modifier
) {
    if (uiState.isLoading) {
        Surface(
            border = BorderStroke(1.dp, gray),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
        ) {
            Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Column {

                    Text(
                        text = "الطقس",
                        style = MaterialTheme.typography.title,
                        fontSize = 16.sp,
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .shimmerEffect()
                        )
                    }
                }
            }
        }
    } else {
        WeatherBox(weather = uiState.weather, modifier = modifier)
    }
}

@Composable
fun WeatherBox(
    weather: WeatherInfo?,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(1.dp, gray),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
            Text(
                text = "جو مناسب لرى نبات العنب",
                style = MaterialTheme.typography.body,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Column {

                Text(
                    text = "الطقس",
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp,
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Column {
                        Text(
                            text = "جودة الهواء",
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "الرياح", style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "هبات الرياح", style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "مقبول",
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = stringResource(id = R.string.wind_unit, weather?.currentWeatherData?.windSpeed.toString()),
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = stringResource(id = R.string.wind_unit, weather?.currentWeatherData?.windSpeed.toString()),
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                // Weather description
                Text(
                    text = stringResource(id = weather?.currentWeatherData?.weatherType?.weatherDesc!!),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.Bottom).padding(8.dp)
                )
                // Weather degree
                Text(
                    text = "${weather.currentWeatherData.temperatureCelsius.toInt()}°",
                    color = greenDark,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
                // Weather icon
                Icon(
                    painter = painterResource(id = weather.currentWeatherData.weatherType.iconRes),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier.height(40.dp)
                )
            }
        }
    }
}

@Composable
fun LatestNewsList(
    uiState: MainUiState,
    isNews: Boolean,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .height(100.dp)
                .background(greenLight)
        )

        LazyRow(state = scrollState) {
            if (uiState.isLoading) {
                items(10) {
                    LatestNewsListItemLoading(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(120.dp)
                            .width(160.dp)
                    )
                }
            } else {
                items(uiState.news.size) {
                    LatestNewsListItem(
                        news = if (isNews) uiState.news[it] else uiState.offers[it],
                        modifier = Modifier
                            .padding(8.dp)
                            .height(120.dp)
                            .width(160.dp)
                    )
                }
            }
        }

        // Go To First Button
        Surface(
            shadowElevation = 8.dp,
            shape = CircleShape,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
                    scope.launch {
                        if (firstVisibleItemIndex > 2) {
                            scrollState.animateScrollToItem(firstVisibleItemIndex - 3)
                        } else {
                            scrollState.animateScrollToItem(0)
                        }
                    }
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Go To Last Button
        Surface(
            shadowElevation = 8.dp,
            shape = CircleShape,
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
                .clickable { scope.launch { scrollState.animateScrollToItem(uiState.news.size) } }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LatestNewsListItemLoading(
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            // Item Image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .shimmerEffect()
            )

            // Item Title
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 14.dp)
                    .height(10.dp)
                    .fillMaxWidth()
                    .shimmerEffect()
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
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            // Item Image
            EmptyImage(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )

            // Item Title
            Text(
                text = news.title,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                color = greenDark,
                maxLines = 1,
                style = MaterialTheme.typography.body,
                fontSize = 11.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 14.dp)
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
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        modifier = modifier.clickable { onItemClick() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Card Title
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                // Card Icon
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Card Description
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.body,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
            )

            body()
        }
    }
}

@Composable
fun ProblemImagesRow(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        ProblemImagesRowItem()
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = greenDark,
            modifier = Modifier.padding(12.dp)
        )
        ProblemImagesRowItem()
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = greenDark,
            modifier = Modifier.padding(12.dp)
        )
        ProblemImagesRowItem()
    }
}

@Composable
fun ProblemImagesRowItem(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(70.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(gray)
    ) {
        Text(text = "ico", fontSize = 21.sp, color = white)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentLoadingPreview() {
    MainScreenContent(
        uiState = MainUiState(
            weather = null,
            news = fakeNews
        ),
        openScreen = { }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        uiState = MainUiState(
            weather = null,
            news = fakeNews,
            isLoading = false
        ),
        openScreen = { }
    )
}

val fakeNews = listOf(
    News(title = "ابتكار طرق رى جديدة"),
    News(title = "ابتكار طرق رى جديدة"),
    News(title = "ابتكار طرق رى جديدة"),
)