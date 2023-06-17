package com.example.agrican.ui.screens.home.main

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.Weather
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
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
                .padding(MaterialTheme.spacing.medium)
                .clickable { openScreen(WeatherDestination.route) }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Latest News Button
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            ) {
                Text(
                    text = stringResource(id = R.string.last_news),
                    fontSize = MaterialTheme.spacing.sp_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }

            // Latest Offers Button
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.last_offers),
                    color = greenDark,
                    fontSize = MaterialTheme.spacing.sp_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }
        }

        LatestNewsList(news = news)

        // Problem Images Card
        BottomCard(
            title = R.string.problem_images,
            description = R.string.problem_images_description,
            icon = R.drawable.camera,
            onItemClick = { openScreen(ProblemImagesDestination.route) },
            body = { ProblemImagesRow() },
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth()

        )

        Row {
            // Fertilizers Calculator Card
            BottomCard(
                title = R.string.fertilizers_calculator,
                description = R.string.fertilizers_calculator_description,
                icon = R.drawable.calculator,
                onItemClick = { openScreen(FertilizersCalculatorDestination.route) },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
            )

            // Ask An Expert Card
            BottomCard(
                title = R.string.ask_expert,
                description = R.string.ask_expert_description,
                icon = R.drawable.ask_expert,
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
        border = BorderStroke(MaterialTheme.spacing.dp_1, Color.Gray),
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "الطقس",
                    style = MaterialTheme.typography.title,
                    fontSize = MaterialTheme.spacing.sp_16,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = weather.firstInformation,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_11
                )
            }

            Row {
                Column {
                    Text(
                        text = "جودة الهواء",
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "الرياح", style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "هبات الرياح", style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    Text(
                        text = weather.air,
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = weather.wind,
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = weather.windGusts,
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = weather.air,
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_11,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                )
                Text(
                    text = "${weather.degree.toInt()}°",
                    color = greenDark,
                    fontSize = MaterialTheme.spacing.sp_30,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                )
                Icon(
                    painter = painterResource(id = R.drawable.sunny),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.extraSmall)
                        .height(MaterialTheme.spacing.large)
                )
            }
        }
    }
}

@Composable
fun LatestNewsList(
    news: List<News>,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .height(MaterialTheme.spacing.dp_80)
                .background(greenDark)
        )
        LazyRow(state = scrollState) {
            items(news.size) {
                LatestNewsListItem(
                    news = news[it],
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .height(MaterialTheme.spacing.dp_100)
                        .width(MaterialTheme.spacing.dp_150)
                )
            }
        }

        // Go To First Button
        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = CircleShape,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
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
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )
        }

        // Go To Last Button
        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = CircleShape,
            modifier = Modifier
                .padding(end = MaterialTheme.spacing.medium)
                .align(Alignment.CenterEnd)
                .clickable { scope.launch { scrollState.animateScrollToItem(news.size) } }
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
                fontSize = MaterialTheme.spacing.sp_11,
                fontWeight = FontWeight.Bold,
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
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Card Title
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.title,
                    fontSize = MaterialTheme.spacing.sp_16
                )
                Spacer(modifier = Modifier.weight(1f))
                // Card Icon
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier.size(MaterialTheme.spacing.dp_24)
                )
            }
            // Card Description
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.body,
                fontSize = MaterialTheme.spacing.sp_11,
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
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .fillMaxWidth()
    ) {
        ProblemImagesRowItem()
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = greenDark
        )
        ProblemImagesRowItem()
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = greenDark,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
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
            .size(MaterialTheme.spacing.dp_75)
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .background(gray)
    ) {
        Text(text = "ico", color = white)
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
        News(title = "ابتكار طرق رى جديدة"),
        News(title = "ابتكار طرق رى جديدة"),
        News(title = "ابتكار طرق رى جديدة"),
    )
    MainScreenContent(weather = weather, news = news, openScreen = { })
}