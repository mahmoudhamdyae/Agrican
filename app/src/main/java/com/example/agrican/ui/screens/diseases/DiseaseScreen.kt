package com.example.agrican.ui.screens.diseases

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.DescriptionLabel
import com.example.agrican.ui.components.DiseaseHeader
import com.example.agrican.ui.components.MainLabel
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.spacing

object DiseaseDestination: NavigationDestination {
    override val route: String = "disease"
    override val titleRes: Int = R.string.disease
}

@Composable
fun DiseaseScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    BackButton(navigateUp = navigateUp) {
        DiseaseScreenContent(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun DiseaseScreenContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        DiseaseHeader(
            image = R.drawable.ic_sunny,
            diseaseName = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
            buttonText = R.string.search_for_another_disease,
        )

        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            MainLabel(text = R.string.naming)
            DescriptionLabel(texts = listOf("تيكا"))

            MainLabel(text = R.string.description)
            DescriptionLabel(
                texts = listOf(
                    "تظهر بقع سوداء أو بنيةعلى الأوراق السفلية لنبات الفول السودانى",
                    "البقع قد تكون صغيرة أو كبيرة و تتواجد بشكل منتشر أو متجمع"
                )
            )

            MainLabel(text = R.string.reasons)
            DescriptionLabel(
                texts = listOf(
                    "جفاف أو نقص الماء: نقص الماء فى التربة يؤدى إلى ضعف النبات و ظهور تبقع الأوراق",
                    "تربة غير مناسبة: تربة فقيرة بالعناصر الغذائية أو غير متوازنة تؤثر على صحة النبات و تزيد من احتمالية ظهور التيكا",
                    "الأمراض الفطرية: بعض الفطريات المسببة للأمراض تسبب تبقع الأوراق فى الفول السودانى"
                )
            )

            MainLabel(text = R.string.effect_on_plants)
            DescriptionLabel(
                texts = listOf(
                    "تقليل النمو و ضعف النبات",
                    "تأثير سلبى على إنتاجية النبات وجودة المحصول النهائى"
                )
            )

            MainLabel(text = R.string.cure)
            DescriptionLabel(
                texts = listOf(
                    "ضمان رى منتظم ومناسب للنبات",
                    "نحسين جودة التربة وتوفير العناصر الغذائية اللازمة",
                    "استخدام مبيدات فطرية للسيطرة على الأمراض الفطرية",
                    "إجراء مراقبة دورية للنباتات والتدخل المبكر فى حالة ظهور التيكا"
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiseaseScreenPreview() {
    DiseaseScreen(navigateUp = { })
}