package com.example.agrican.ui.screens.home.agricanservices

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.default_age.DefaultAgesDestination
import com.example.agrican.ui.screens.diseases.DiseasesDestination
import com.example.agrican.ui.screens.join_as_expert.JoinAsExpertDestination
import com.example.agrican.ui.screens.order.OrderDestination
import com.example.agrican.ui.screens.pests.PestsDestination
import com.example.agrican.ui.screens.treatment.TreatmentDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object AgricanServicesDestination: NavigationDestination {
    override val route: String = "agrican_services"
    override val titleRes: Int = R.string.default_age_title
}

@Composable
fun AgricanServicesScreen(
    openScreen: (String) -> Unit,
    setTopBarTitle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(greenLight)
            )
            Row {
                Card(
                    title = R.string.default_age,
                    description = R.string.problem_images_description,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(MaterialTheme.spacing.medium)
                        .clickable {
                            openScreen(DefaultAgesDestination.route)
                            setTopBarTitle(DefaultAgesDestination.titleRes)
                                   },
                    isPrimaryMain = true
                )
                Card(
                    title = R.string.order,
                    description = R.string.problem_images_description,
                    modifier = Modifier
                        .weight(1f)
                        .padding(MaterialTheme.spacing.medium)
                        .clickable {
                            openScreen(OrderDestination.route)
                            setTopBarTitle(OrderDestination.titleRes)
                                   },
                    isPrimaryMain = true
                )
            }
        }

        Box(modifier = Modifier.background(greenLight)) {
            Column(modifier = Modifier) {
                Row {
                    Card(
                        title = R.string.disease,
                        description = R.string.problem_images_description,
                        modifier = Modifier
                            .weight(1f)
                            .padding(MaterialTheme.spacing.medium)
                            .clickable {
                                openScreen(DiseasesDestination.route)
                                setTopBarTitle(DiseasesDestination.titleRes)
                            }
                    )
                    Card(
                        title = R.string.pests,
                        description = R.string.problem_images_description,
                        modifier = Modifier
                            .weight(1.5f)
                            .padding(MaterialTheme.spacing.medium)
                            .clickable {
                                openScreen(PestsDestination.route)
                                setTopBarTitle(PestsDestination.titleRes)
                            }
                    )
                }
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(greenDark)
                    )
                    Card(
                        title = R.string.treatment,
                        description = R.string.problem_images_description,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                            .clickable {
                                openScreen(TreatmentDestination.route)
                                setTopBarTitle(TreatmentDestination.titleRes)
                            }
                    )
                }
            }
        }

        Card(
            title = R.string.join_as_expert,
            description = R.string.problem_images_description,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .clickable {
                    openScreen(JoinAsExpertDestination.route)
                    setTopBarTitle(JoinAsExpertDestination.titleRes)
                }
        )
    }
}

@Composable
fun Card(
    title: Int,
    description: Int,
    modifier: Modifier = Modifier,
    isPrimaryMain: Boolean = false
) {
    val mainColor = if (isPrimaryMain) greenDark
                    else MaterialTheme.colorScheme.background
    val secondaryColor = if (isPrimaryMain) MaterialTheme.colorScheme.background
                    else greenDark

    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        shadowElevation = MaterialTheme.spacing.medium,
        color = mainColor,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Text(
                text = stringResource(id = title),
                fontWeight = FontWeight.Bold,
                color = secondaryColor
            )
            
            Text(text = stringResource(id = description))

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    shape = CircleShape,
                    color = secondaryColor
                ) {
                    Text(
                        text = "ico",
                        color = mainColor,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgricanServiceScreenPreview() {
    AgricanServicesScreen(openScreen = { }, setTopBarTitle = { })
}