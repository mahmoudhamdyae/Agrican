package com.example.agrican.ui.screens.home.agricanservices.pests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.agrican.R
import com.example.agrican.domain.model.Pest
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.DescriptionLabel
import com.example.agrican.ui.components.DiseaseHeader
import com.example.agrican.ui.components.MainLabel
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.spacing

object PestDestination: NavigationDestination {
    override val route: String = "pest"
    override val titleRes: Int = R.string.pests
    const val pestIdArg = "pest_id"
    val routeWithArgs = "$route/{$pestIdArg}"
    val arguments = listOf(
        navArgument(pestIdArg) { type = NavType.StringType },
    )
}

@Composable
fun PestScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PestViewModel = hiltViewModel()
) {
    val pest by viewModel.pest.collectAsStateWithLifecycle()

    BackButton(navigateUp = navigateUp) {
        PestScreenContent(
            pest = pest,
            navigateUp = navigateUp,
            modifier = modifier
        )
    }
}

@Composable
fun PestScreenContent(
    pest: Pest,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        DiseaseHeader(
            image = R.drawable.ic_sunny,
            diseaseName = "ثاقبة ساق الأرز\nRice Stem Borer",
            buttonText = R.string.search_for_another_pest,
            onButtonClick = navigateUp
        )

        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {

            MainLabel(text = R.string.scientific_name)
            DescriptionLabel(texts = listOf(pest.name))

            MainLabel(text = R.string.category)
            // todo: Lazy List
            Row(modifier = Modifier.fillMaxWidth()) {
                CategoryItem(
                    categoryName = "Order Lepidoptera (فراشات)",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                CategoryItem(
                    categoryName = "Family Crambidae (العواكس)",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    categoryName = "Superfamily Pyraloidea",
                    modifier = Modifier.weight(1f)
                )
            }

            MainLabel(text = R.string.main_host)
            // todo: Lazy List
            Row(modifier = Modifier.fillMaxWidth()) {
                MainHostItem(
                    text = "نبات الأرز\nOryza sativa",
                    modifier = Modifier.weight(1f)
                )
                MainHostItem(
                    text = "نبات الأرز\nOryza sativa",
                    modifier = Modifier.weight(1f)
                )
            }

            MainLabel(text = R.string.life_cycle)
            DescriptionLabel(texts = pest.lifeCycle)

            MainLabel(text = R.string.damages)
            DescriptionLabel(texts = pest.damages)

            MainLabel(text = R.string.signs_and_symptoms)
            DescriptionLabel(texts = pest.symptoms)

            MainLabel(text = R.string.injury_season)
            DescriptionLabel(texts = listOf(pest.injurySeason))

            MainLabel(text = R.string.control_timing)
            DescriptionLabel(texts = pest.controlTiming)
        }
    }
}

@Composable
fun CategoryItem(
    categoryName: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.small),
        border = BorderStroke(1.dp, gray),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = categoryName,
            modifier = modifier.padding(MaterialTheme.spacing.small)
        )
    }
}

@Composable
fun MainHostItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        color = gray,
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .padding(MaterialTheme.spacing.medium)
    ) {
        Box {
            Image(painter = painterResource(id = R.drawable.ic_sunny), contentDescription = null)
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(MaterialTheme.spacing.small)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PestScreenPreview() {
    PestScreenContent(pest = Pest(), navigateUp = { })
}