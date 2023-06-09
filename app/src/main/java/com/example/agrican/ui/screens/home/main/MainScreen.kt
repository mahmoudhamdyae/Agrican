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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.domain.model.Weather
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
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
    modifier: Modifier = Modifier
) {
    MainScreenContent(openScreen = openScreen, modifier = modifier)
}

@Composable
fun MainScreenContent(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        WeatherBox(
            weather = Weather(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.large)
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

        LatestNewsList()

        BottomCard(
            title = R.string.problem_images,
            description = R.string.problem_images_description,
            icon = R.drawable.ic_visibility_on,
            onItemClick = { openScreen(ProblemImagesDestination.route) },
            modifier = Modifier.padding(MaterialTheme.spacing.small).fillMaxWidth()

        )

        Row {
            BottomCard(
                title = R.string.fertilizers_calculator,
                description = R.string.fertilizers_calculator_description,
                icon = R.drawable.ic_visibility_on,
                onItemClick = { openScreen(FertilizersCalculatorDestination.route) },
                modifier = Modifier.padding(MaterialTheme.spacing.small).weight(1f)
            )

            BottomCard(
                title = R.string.ask_expert,
                description = R.string.ask_expert_description,
                icon = R.drawable.ic_visibility_on,
                onItemClick = { openScreen(AskExpertDestination.route) },
                modifier = Modifier.padding(MaterialTheme.spacing.small).weight(1f)
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
                Text(text = "مقبول")
                Text(text = "جنوبية غربية 33 كم/س")
                Text(text = "47 كم/س")
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
            ) {
                Text(text = "جو مناسب لرى نبات العنب")
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
            items(9) {
                LatestNewsListItem(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .size(100.dp)
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
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_visibility_on),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )
            Text(
                text = "ابتكار طرق جديدة",
                modifier = Modifier
                    .fillMaxWidth()
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
    MainScreenContent(openScreen = { })
}