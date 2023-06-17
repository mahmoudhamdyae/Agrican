package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.agrican.R
import com.example.agrican.domain.model.Disease
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.DescriptionLabel
import com.example.agrican.ui.components.DiseaseHeader
import com.example.agrican.ui.components.MainLabel
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.spacing

object DiseaseDestination: NavigationDestination {
    override val route: String = "disease"
    override val titleRes: Int = R.string.disease
    const val diseaseIdArg = "disease_id"
    val routeWithArgs = "$route/{$diseaseIdArg}"
    val arguments = listOf(
        navArgument(diseaseIdArg) { type = NavType.StringType },
    )
}

@Composable
fun DiseaseScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiseaseViewModel = hiltViewModel()
) {

    val disease by viewModel.disease.collectAsStateWithLifecycle()

    BackButton(navigateUp = navigateUp) {
        DiseaseScreenContent(
            disease = disease,
            navigateUp = navigateUp,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun DiseaseScreenContent(
    disease: Disease,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = MaterialTheme.spacing.dp_60)
    ) {
        DiseaseHeader(
            image = null,
            diseaseName = disease.name,
            buttonText = R.string.search_for_another_disease,
            onButtonClick = navigateUp
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            // Naming
            MainLabel(text = R.string.naming)
            DescriptionLabel(texts = listOf(disease.name))

            // Description
            MainLabel(text = R.string.description)
            DescriptionLabel(texts = disease.description)

            // Reasons
            MainLabel(text = R.string.reasons)
            DescriptionLabel(texts = disease.reasons)

            // Effects
            MainLabel(text = R.string.effect_on_plants)
            DescriptionLabel(texts = disease.effects)

            // Cure
            MainLabel(text = R.string.cure)
            DescriptionLabel(texts = disease.cure)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiseaseScreenPreview() {
    DiseaseScreenContent(disease = Disease(), navigateUp = { })
}