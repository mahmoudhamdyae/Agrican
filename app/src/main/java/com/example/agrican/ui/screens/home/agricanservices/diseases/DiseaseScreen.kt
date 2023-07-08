package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.agrican.ui.theme.white

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
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun DiseaseScreenContent(
    disease: Disease,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(white)
    ) {
        DiseaseHeader(
            image = null,
            diseaseName = disease.title,
            buttonText = R.string.search_for_another_disease,
            onSelect = { /*TODO*/ }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
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
    DiseaseScreenContent(
        disease = Disease(
            title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
            name = "تيكا",
            description = listOf(
                "تظهر بقع سوداء أو بنيةعلى الأوراق السفلية لنبات الفول السودانى",
                "البقع قد تكون صغيرة أو كبيرة و تتواجد بشكل منتشر أو متجمع"
            ),
            reasons = listOf(
                "جفاف أو نقص الماء: نقص الماء فى التربة يؤدى إلى ضعف النبات و ظهور تبقع الأوراق",
                "تربة غير مناسبة: تربة فقيرة بالعناصر الغذائية أو غير متوازنة تؤثر على صحة النبات و تزيد من احتمالية ظهور التيكا",
                "الأمراض الفطرية: بعض الفطريات المسببة للأمراض تسبب تبقع الأوراق فى الفول السودانى"
            ),
            effects = listOf(
                "تقليل النمو و ضعف النبات",
                "تأثير سلبى على إنتاجية النبات وجودة المحصول النهائى"
            ),
            cure = listOf(
                "ضمان رى منتظم ومناسب للنبات",
                "نحسين جودة التربة وتوفير العناصر الغذائية اللازمة",
                "استخدام مبيدات فطرية للسيطرة على الأمراض الفطرية",
                "إجراء مراقبة دورية للنباتات والتدخل المبكر فى حالة ظهور التيكا"
            )
        )
    )
}