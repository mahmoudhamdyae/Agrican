package com.example.agrican.ui.screens.diseases

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
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

    Box(modifier = modifier) {
        DiseaseScreenContent(modifier = Modifier.fillMaxSize())
        IconButton(
            onClick = {
                navigateUp()
            },
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .clip(CircleShape)
                .background(greenDark)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun DiseaseScreenContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Surface(
            color = gray,
            shape = RoundedCornerShape(
                bottomStart = MaterialTheme.spacing.large,
                bottomEnd = MaterialTheme.spacing.large,
            ),
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sunny),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    Text(
                        text = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.weight(1f)
                    ) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.search_for_another_disease),
                                color = greenDark
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                contentDescription = null,
                                tint = greenDark
                            )
                        }
                    }
                }
            }
        }

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

@Composable
fun MainLabel(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        color = greenDark,
        modifier = modifier
    )
}

@Composable
fun DescriptionLabel(
    texts: List<String>,
    modifier: Modifier = Modifier
) {
    texts.forEach {
        Row(modifier = modifier) {
            if (texts.size > 1) {
                Box(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .clip(CircleShape)
                        .background(Color.Black)
                        .size(MaterialTheme.spacing.extraSmall)
                )
            }
            Text(text = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiseaseScreenPreview() {
    DiseaseScreen(navigateUp = { })
}