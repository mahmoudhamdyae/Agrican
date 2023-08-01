package com.theflankers.agrican.ui.screens.home.agricanservices.pests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.model.Pest
import com.theflankers.agrican.ui.components.BackButton
import com.theflankers.agrican.ui.components.DescriptionLabel
import com.theflankers.agrican.ui.components.DiseaseHeader
import com.theflankers.agrican.ui.components.EmptyImage
import com.theflankers.agrican.ui.components.MainLabel
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.white

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
            modifier = modifier.padding(bottom = 60.dp)
        )
    }
}

@Composable
fun PestScreenContent(
    pest: Pest,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(white)
    ) {
        DiseaseHeader(
            image = null,
            diseaseName = "ثاقبة ساق الأرز\nRice Stem Borer",
            buttonText = R.string.search_for_another_pest,
            onSelect = { /*TODO*/ }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(24.dp)
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)
            ) {
                CategoryItem(
                    categoryName = "Family Crambidae (العواكس)",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    categoryName = "Superfamily Pyraloidea",
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
            }

            // Main Label
            MainLabel(text = R.string.main_host)
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().height(150.dp)
            ) {
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
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = categoryName,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
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
        modifier = modifier.clip(RoundedCornerShape(16.dp))
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
    PestScreenContent(
        pest = Pest(
            title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
            name = "Scirpophaga inceertulas-Chilo suppressalis",
            categories = listOf(
                "Order Lepidoptera (فراشات)",
                "Family Crambidae (العواكس)",
                "Superfamily Pyraloidea"
            ),
            mainHosts = listOf(
                Crop(name = "نبات الأرز\nOryza sativa"),
                Crop(name = "نبات الأرز\nOryza sativa")
            ),
            lifeCycle = listOf(
                "اليرقات: تتغذى على سيقان الأرز الشابة والأوراق",
                "البلابل (الفراشات): تضع بيضها على أوراق الأرز",
                "اليرقات الجديدة: تحفر فى سيقان النبات وتتغذى داخلها"
            ),
            damages = listOf(
                "القلب الميت: تسبب جفاف البرعم المركزى للشتلات وتموت بعد ذلك",
                "السنابل البيضاء: تتلف الحبوب فى المراكز المبكرة وتسبب تدهور جودة السنابل"
            ),
            symptoms = listOf(
                "وجود كتلة بيضاء بالقرب من طرف الورقة",
                "جفاف البراعم المركزية (القلب الميت)",
                "تصبح العناقيد الكاملة مجففة (السنابل البيضاء)",
                "تظهر بقع بيضاء طولية على أغماد الورقة"
            ),
            injurySeason = "يهاجم الأرز خلال جميع مراحل نموه، بدءًا من طور البادرة و حتى تكوين السنابل",
            controlTiming = listOf(
                "فى الأرز الصيفى: يبدأ المكافحة عند عمر 25 يوم وقبل وصول نسبة القلب الميت إلى 5%",
                "فى الأرز الشتوى: تبدأ المكافحة بعد حوالى 40 يومًا من الشتل وقبل وصول نسبة القلب الميت إلى 5%"
            )
        )
    )
}