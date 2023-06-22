package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Disease
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.white

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
        modifier = modifier.padding(8.dp)
    ) {
        items(diseases) { disease ->
            DiseaseListItem(disease = disease, onItemClick = onItemClick)
        }

        // Spacer for Bottom Navigation Bar
        item {
            Spacer(modifier = Modifier.height(60.dp))
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
        shape = RoundedCornerShape(16.dp),
        color = gray,
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onItemClick(disease.diseaseId)
            }
            .padding(8.dp)
    ) {
        Box {
            // Disease Image
            EmptyImage()

            // Disease Title
            Text(
                text = disease.title,
                color = white,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiseasesScreenPreview() {
    DiseaseList(
        diseases = listOf(
            Disease(title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"),
            Disease(title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"),
            Disease(title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى")
        ),
        onItemClick = { }
    )
}