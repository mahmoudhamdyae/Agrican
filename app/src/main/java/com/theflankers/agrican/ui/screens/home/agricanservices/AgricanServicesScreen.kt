package com.theflankers.agrican.ui.screens.home.agricanservices

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.R
import com.theflankers.agrican.ui.components.TopBar
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.default_age.DefaultAgesDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.diseases.DiseasesDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.join_as_expert.JoinAsExpertDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.order.OrderDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.pests.PestsDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.treatment.TreatmentDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.textGray
import com.theflankers.agrican.ui.theme.title
import com.theflankers.agrican.ui.theme.white

object AgricanServicesDestination: NavigationDestination {
    override val route: String = "agrican_services"
    override val titleRes: Int = R.string.navigation_agrican_services
}

@Composable
fun AgricanServicesScreen(
    openScreen: (String) -> Unit,
    navigateFromSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        title = {
            Text(
                text = stringResource(id = R.string.navigation_agrican_services),
                color = textGray,
                style = MaterialTheme.typography.title
            )
        },
        openScreen = openScreen,
        navigateFromSignOut = navigateFromSignOut
    ) {
        AgricanServicesContent(openScreen = openScreen, modifier = modifier)
    }
}

@Composable
fun AgricanServicesContent(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxSize()
                    .background(greenLight)
            )
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 16.dp)
            ) {
                // Default Age
                Card(
                    title = R.string.default_age,
                    description = R.string.default_age_description,
                    icon = R.drawable.default_age,
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                        .clickable { openScreen(DefaultAgesDestination.route) },
                    isPrimaryMain = true
                )

                // Orders
                Card(
                    title = R.string.order,
                    description = R.string.order_description,
                    icon = R.drawable.order_product,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { openScreen(OrderDestination.route) },
                    isPrimaryMain = true
                )
            }
        }

        Box(modifier = Modifier.background(greenLight)) {
            // Diseases
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Row(modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 16.dp)) {
                    Card(
                        title = R.string.disease,
                        description = R.string.disease_description,
                        icon = R.drawable.diseases,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                            .clickable { openScreen(DiseasesDestination.route) }
                    )

                    // Pests
                    Card(
                        title = R.string.pests,
                        description = R.string.pests_description,
                        icon = R.drawable.pests,
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight()
                            .clickable { openScreen(PestsDestination.route) }
                    )
                }

                Box(modifier = Modifier.height(IntrinsicSize.Max)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 26.dp)
                            .background(greenDark)
                    )
                    // Treatment
                    Card(
                        title = R.string.treatment,
                        description = R.string.treatment_description,
                        icon = R.drawable.treatment,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { openScreen(TreatmentDestination.route) }
                    )
                }
            }
        }

        // Join As Expert
        Card(
            title = R.string.join_as_expert,
            description = R.string.join_as_expert_description,
            icon = R.drawable.join_as_expert,
            modifier = Modifier
                .padding(16.dp)
                .clickable { openScreen(JoinAsExpertDestination.route) }
        )
    }
}

@Composable
fun Card(
    @StringRes title: Int,
    @StringRes description: Int,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    isPrimaryMain: Boolean = false
) {
    val mainColor = if (isPrimaryMain) greenDark else white
    val secondaryColor = if (isPrimaryMain) white else greenDark

    Surface(
        shape = RoundedCornerShape(11.dp),
        shadowElevation = 16.dp,
        color = mainColor,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxHeight()
        ) {
            // Card Title
            Text(
                text = stringResource(id = title),
                color = secondaryColor,
                style = MaterialTheme.typography.title,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            // Card Description
            Text(
                text = stringResource(id = description),
                color = if (isPrimaryMain) white else textGray,
                style = MaterialTheme.typography.body,
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Card Icon
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = secondaryColor,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgricanServiceScreenPreview() {
    AgricanServicesScreen(openScreen = { }, navigateFromSignOut = { })
}

@Preview
@Composable
fun CardPreview() {
    Card(
        title = R.string.join_as_expert,
        description = R.string.join_as_expert_description,
        icon = R.drawable.join_as_expert
    )
}