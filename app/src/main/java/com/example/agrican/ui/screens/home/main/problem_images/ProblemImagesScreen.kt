package com.example.agrican.ui.screens.home.main.problem_images

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProblemImagesViewModel = hiltViewModel()
) {

    var hasImage by rememberSaveable { mutableStateOf(false) }
    var imageUri1: Uri? by rememberSaveable { mutableStateOf(null) }
    var imageUri2: Uri? by rememberSaveable { mutableStateOf(null) }
    var imageUri3: Uri? by rememberSaveable { mutableStateOf(null) }
    var currentImage: Int by rememberSaveable { mutableStateOf(0) }

    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                hasImage = true
                when (currentImage) {
                    0 -> { imageUri1 = uri }
                    1 -> { imageUri2 = uri }
                    2 -> { imageUri3 = uri }
                    else  -> {}
                }
                currentImage++
            }
        }

    BackButton(navigateUp = navigateUp) {
        ProblemImageScreenContent(
            currentImage = currentImage,
            imageUri1 = imageUri1,
            imageUri2 = imageUri2,
            imageUri3 = imageUri3,
            launchImagePicker = {
                imagePicker.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            onSearch = viewModel::search,
            openScreen = openScreen,
            modifier = modifier
        )
    }
}

@Composable
fun ProblemImageScreenContent(
    currentImage: Int,
    imageUri1: Uri?,
    imageUri2: Uri?,
    imageUri3: Uri?,
    launchImagePicker: () -> Unit,
    onSearch: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
                    openScreen(CameraDestination.route)
                },
                modifier = Modifier.weight(1f)
            )
            WayChoose(
                image = R.drawable.baseline_image_24,
                text = R.string.from_gallery,
                onItemClick = {
                    if (currentImage != 3) launchImagePicker()
                },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onSearch,
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.start_searching),
                    textAlign = TextAlign.Center,
                )
            }
            ImageView(image = imageUri1)
            ImageView(image = imageUri2)
            ImageView(image = imageUri3)

        }

        Text(
            text = stringResource(id = R.string.advices_label),
        )

        Text(text = stringResource(id = R.string.advices))
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
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .clickable {
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
    image: Uri?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(data = image)
            .build(),
        placeholder = painterResource(R.drawable.loading_img),
        error = painterResource(R.drawable.baseline_image_24),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .size(75.dp)
            .padding(MaterialTheme.spacing.small)
            .clip(RoundedCornerShape(MaterialTheme.spacing.small))
    )
}

@Preview(showBackground = true)
@Composable
fun ProblemImageScreenPreview() {
    ProblemImageScreenContent(
        currentImage = 0,
        imageUri1 = null,
        imageUri2 = null,
        imageUri3 = null,
        launchImagePicker = { },
        openScreen = { },
        onSearch = { }
    )
}