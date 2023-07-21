package com.example.agrican.ui.screens.home.agricanservices.pests

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
import com.example.agrican.domain.model.Pest
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.white

object PestsDestination: NavigationDestination {
    override val route: String = "pests"
    override val titleRes: Int = R.string.pests
}

@Composable
fun PestsScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PestsViewModel = hiltViewModel()
) {
    val pests by viewModel.pests.collectAsStateWithLifecycle()

    BackButtonTopBar(
        title = PestsDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        PestList(
            pests = pests,
            onItemClick = { openScreen("${PestDestination.route}/$it") }
        )
    }
}

@Composable
fun PestList(
    pests: List<Pest>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp)
    ) {
        items(items = pests, key = { it.pestId }) { pest ->
            PestListItem(pest = pest, onItemClick = onItemClick)
        }

        // Spacer for Bottom Navigation Bar
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun PestListItem(
    pest: Pest,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = gray,
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                onItemClick(pest.pestId)
            }
            .padding(8.dp)
    ) {
        Box {
            // Pest Image
            EmptyImage()

            // Pest Title
            Text(
                text = pest.title,
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
fun PestsScreenPreview() {
    PestList(
        pests = listOf(
            Pest(title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"),
            Pest(title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"),
            Pest(title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى")
        ),
        onItemClick = { }
    )
}