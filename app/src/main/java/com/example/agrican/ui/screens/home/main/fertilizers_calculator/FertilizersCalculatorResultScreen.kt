package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.GreenBackButton
import com.example.agrican.ui.components.ReturnToMainButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight

object FertilizersCalculatorResultDestination: NavigationDestination {
    override val route: String = "fertilizers_calculator_result"
    override val titleRes: Int = R.string.fertilizers_calculator
}

@Composable
fun FertilizersCalculatorResultScreen(
    navigateUp: () -> Unit,
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.padding(12.dp)
    ) {
        GreenBackButton(navigateUp = navigateUp)
        
        Item(
            mainText = "الكمية المطلوبة من الأسمدة البوتاسية",
            secondaryText = "15 كغ/فدان"
        )

        Item(
            mainText = "الكمية المطلوبة من الأسمدة النيتروجينية",
            secondaryText = "15 كغ/فدان"
        )

        Item(
            mainText = "الكمية المطلوبة من الأسمدة الفوسفاتية",
            secondaryText = "15 كغ/فدان"
        )

        ReturnToMainButton(
            openAndClear = openAndClear,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun Item(
    mainText: String,
    secondaryText: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = mainText,
            color = greenDark,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = secondaryText,
            color = greenLight,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun FertilizersCalculatorResultScreenPreview() {
    FertilizersCalculatorResultScreen(
        navigateUp = { },
        openAndClear = { }
    )
}