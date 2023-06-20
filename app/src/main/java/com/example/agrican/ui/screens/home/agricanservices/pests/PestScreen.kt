package com.example.agrican.ui.screens.home.agricanservices.pests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.agrican.R
import com.example.agrican.domain.model.Pest
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.DescriptionLabel
import com.example.agrican.ui.components.DiseaseHeader
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.components.MainLabel
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.white
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            modifier = modifier.padding(bottom = 60.dp)
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
            image = null,
            diseaseName = "ثاقبة ساق الأرز\nRice Stem Borer",
            buttonText = R.string.search_for_another_pest,
            onButtonClick = navigateUp
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {

            // Scientific Name Label
            MainLabel(text = R.string.scientific_name)
            DescriptionLabel(texts = listOf(pest.name))

            // Category Label
            MainLabel(text = R.string.category)
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

            // Main Label
            MainLabel(text = R.string.main_host)
            Row(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                MainHostItem(
                    text = "نبات الأرز\nOryza sativa",
                    modifier = Modifier.weight(1f)
                )
                MainHostItem(
                    text = "نبات الأرز\nOryza sativa",
                    modifier = Modifier.weight(1f)
                )
            }

            // Life Cycle
            MainLabel(text = R.string.life_cycle)
            DescriptionLabel(texts = pest.lifeCycle)

            // Damages
            MainLabel(text = R.string.damages)
            DescriptionLabel(texts = pest.damages)

            // Symptoms
            MainLabel(text = R.string.signs_and_symptoms)
            DescriptionLabel(texts = pest.symptoms)

            // Injury Season
            MainLabel(text = R.string.injury_season)
            DescriptionLabel(texts = listOf(pest.injurySeason))

            // Control Timing
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
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, gray),
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = categoryName,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(8.dp)
        )
    }
}

@Composable
fun MainHostItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = gray,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Box {
            EmptyImage(modifier = Modifier.fillMaxSize())
            Text(
                text = text,
                color = white,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PestScreenPreview() {
    PestScreenContent(pest = Pest(), navigateUp = { })
}