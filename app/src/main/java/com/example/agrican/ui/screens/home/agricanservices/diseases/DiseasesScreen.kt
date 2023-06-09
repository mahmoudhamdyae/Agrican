package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
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
    DiseasesScreenContent(
        navigateUp = navigateUp,
        onItemClick = { openScreen(DiseaseDestination.route) },
        modifier = modifier
    )
}

@Composable
fun DiseasesScreenContent(
    navigateUp: () -> Unit,
    onItemClick: (/*item*/) -> Unit,
    modifier: Modifier = Modifier
) {
    BackButton(navigateUp = navigateUp) {
        DiseaseList(
            diseases = listOf(1, 2, 3, 4, 5, 6, 7, 8),
            onItemClick = onItemClick,
            modifier = modifier
        )
    }
}

@Composable
fun DiseaseList(
    diseases: List<Int>,
    onItemClick: (/*item*/) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        items(diseases) { disease ->
            DiseaseListItem(onItemClick = onItemClick)
        }
    }
}

@Composable
fun DiseaseListItem(
    onItemClick: (/*item*/) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        color = gray,
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .clickable {
                onItemClick(/*item*/)
            }
            .padding(MaterialTheme.spacing.medium)
    ) {
        Box {
            Image(painter = painterResource(id = R.drawable.ic_sunny), contentDescription = null)
            Text(
                text = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
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
fun DiseasesScreenPreview() {
    DiseasesScreen(navigateUp = { }, openScreen = { })
}