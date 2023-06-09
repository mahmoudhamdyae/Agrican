package com.example.agrican.ui.screens.home.main.problem_images

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object ProblemImagesDestination: NavigationDestination {
    override val route: String = "problem_images"
    override val titleRes: Int = R.string.problem_images
}

@Composable
fun ProblemImagesScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    BackButton(navigateUp = navigateUp) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    top = MaterialTheme.spacing.large,
                    start = MaterialTheme.spacing.medium
                )
        ) {
            Text(
                text = stringResource(id = R.string.choose_plant_type),
                color = greenDark
            )
            CropsList()
            Text(
                text = stringResource(id = R.string.choose_problem_image_way),
                color = greenDark
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                WayChoose(
                    image = R.drawable.baseline_camera_alt_24,
                    text = R.string.from_camera,
                    onItemClick = {
                        // todo: camera
                    },
                    modifier = Modifier.weight(1f)
                )
                WayChoose(
                    image = R.drawable.baseline_image_24,
                    text = R.string.from_gallery,
                    onItemClick = {
                        // todo: gallery
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.start_searching),
                        textAlign = TextAlign.Center,
                    )
                }
                repeat(3) {
                    ImageView(modifier = Modifier.weight(1f))
                }
            }

            Text(
                text = stringResource(id = R.string.advices_label),
            )

            Text(text = stringResource(id = R.string.advices))
        }
    }
}

@Composable
fun WayChoose(
    image: Int,
    text: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.small),
        border = BorderStroke(1.dp, greenDark),
        modifier = modifier.padding(MaterialTheme.spacing.medium).clickable {
            onItemClick()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.large,)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
            )
            Text(
                text = stringResource(id = text),
                color = greenDark
            )
        }
    }
}

@Composable
fun ImageView(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_sunny),
        contentDescription = null,
        modifier = modifier.size(50.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProblemImageScreenPreview() {
    ProblemImagesScreen(navigateUp = { })
}