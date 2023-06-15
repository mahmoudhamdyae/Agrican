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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Disease
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
    modifier: Modifier = Modifier,
    viewModel: DiseasesViewModel= hiltViewModel()
) {
    val diseases by viewModel.diseases.collectAsStateWithLifecycle()

    BackButton(navigateUp = navigateUp) {
        DiseaseList(
            diseases = diseases,
            onItemClick = { openScreen("${DiseaseDestination.route}/$it") },
            modifier = modifier
        )
    }
}

@Composable
fun DiseaseList(
    diseases: List<Disease>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        items(diseases) { disease ->
            DiseaseListItem(disease = disease, onItemClick = onItemClick)
        }
    }
}

@Composable
fun DiseaseListItem(
    disease: Disease,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        color = gray,
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .clickable {
                onItemClick(disease.diseaseId)
            }
            .padding(MaterialTheme.spacing.medium)
    ) {
        Box {
            // Disease Image
            Image(painter = painterResource(id = R.drawable.ic_sunny), contentDescription = null)

            // Disease Title
            Text(
                text = disease.title,
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
    DiseaseList(diseases = listOf(), onItemClick = { })
}