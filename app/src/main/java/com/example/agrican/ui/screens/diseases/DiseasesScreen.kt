package com.example.agrican.ui.screens.diseases

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object DiseasesDestination: NavigationDestination {
    override val route: String = "diseases"
    override val titleRes: Int = R.string.disease
}

@Composable
fun DiseasesScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        DiseaseList(
            diseases = listOf(1, 2, 3, 4, 5, 6, 7, 8),
            openScreen = openScreen
        )

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
fun DiseaseList(
    diseases: List<Int>,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        items(diseases) { disease ->
            DiseaseListItem(openScreen = openScreen)
        }
    }
}

@Composable
fun DiseaseListItem(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .clickable {
                openScreen(DiseaseDestination.route)
            }
    ) {
        Image(painter = painterResource(id = R.drawable.ic_sunny), contentDescription = null)
        Text(
            text = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiseasesScreenPreview() {
    DiseasesScreen(navigateUp = { }, openScreen = { })
}