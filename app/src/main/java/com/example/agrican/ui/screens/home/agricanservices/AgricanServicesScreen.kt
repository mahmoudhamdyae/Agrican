package com.example.agrican.ui.screens.home.agricanservices

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.agricanservices.default_age.DefaultAgesDestination
import com.example.agrican.ui.screens.home.agricanservices.diseases.DiseasesDestination
import com.example.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertDestination
import com.example.agrican.ui.screens.home.agricanservices.order.OrderDestination
import com.example.agrican.ui.screens.home.agricanservices.pests.PestsDestination
import com.example.agrican.ui.screens.home.agricanservices.treatment.TreatmentDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

object AgricanServicesDestination: NavigationDestination {
    override val route: String = "agrican_services"
    override val titleRes: Int = R.string.default_age_title
}

@Composable
fun AgricanServicesScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box {
            Box(
                modifier = Modifier.fillMaxWidth().background(greenLight)
            )
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                // Default Age
                Card(
                    title = R.string.default_age,
                    description = R.string.problem_images_description,
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxHeight()
                        .padding(MaterialTheme.spacing.medium)
                        .clickable { openScreen(DefaultAgesDestination.route) },
                    isPrimaryMain = true
                )

                // Orders
                Card(
                    title = R.string.order,
                    description = R.string.problem_images_description,
                    modifier = Modifier
                        .weight(1f)
                        .padding(MaterialTheme.spacing.medium)
                        .clickable { openScreen(OrderDestination.route) },
                    isPrimaryMain = true
                )
            }
        }

        Box(modifier = Modifier.background(greenLight)) {
            // Diseases
            Column(modifier = Modifier) {
                Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                    Card(
                        title = R.string.disease,
                        description = R.string.problem_images_description,
                        modifier = Modifier
                            .weight(1f)
                            .padding(MaterialTheme.spacing.medium)
                            .clickable { openScreen(DiseasesDestination.route) }
                    )

                    // Pests
                    Card(
                        title = R.string.pests,
                        description = R.string.problem_images_description,
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight()
                            .padding(MaterialTheme.spacing.medium)
                            .clickable { openScreen(PestsDestination.route) }
                    )
                }

                Box(modifier = Modifier.height(IntrinsicSize.Max)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = MaterialTheme.spacing.large)
                            .background(greenDark)
                    )
                    // Treatmen
                    Card(
                        title = R.string.treatment,
                        description = R.string.problem_images_description,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                            .clickable { openScreen(TreatmentDestination.route) }
                    )
                }
            }
        }

        // Join As Expert
        Card(
            title = R.string.join_as_expert,
            description = R.string.problem_images_description,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .clickable { openScreen(JoinAsExpertDestination.route) }
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
    val mainColor = if (isPrimaryMain) greenDark else white
    val secondaryColor = if (isPrimaryMain) white else greenDark

    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        shadowElevation = MaterialTheme.spacing.medium,
        color = mainColor,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            // Card Title
            Text(
                text = stringResource(id = title),
                color = secondaryColor,
                style = MaterialTheme.typography.title,
                fontSize = MaterialTheme.spacing.sp_16,
            )

            // Card Description
            Text(
                text = stringResource(id = description),
                color = if (isPrimaryMain) white else textGray,
                style = MaterialTheme.typography.body,
                fontSize = MaterialTheme.spacing.sp_11
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            // Card Icon
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

@Preview(showBackground = true)
@Composable
fun AgricanServiceScreenPreview() {
    AgricanServicesScreen(openScreen = { })
}