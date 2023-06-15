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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
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
    openCamera: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProblemImagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var shouldShowCamera by rememberSaveable { mutableStateOf(false) }

    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                when (uiState.currentImage) {
                    0 -> { viewModel.updateUiState(image1 = uri) }
                    1 -> { viewModel.updateUiState(image2 = uri) }
                    2 -> { viewModel.updateUiState(image3 = uri) }
                    else  -> {}
                }
                viewModel.updateUiState(currentImage = uiState.currentImage + 1)
            }
        }

    if (!shouldShowCamera) {
        BackButton(navigateUp = navigateUp) {
            val context = LocalContext.current

            ProblemImageScreenContent(
                uiState = uiState,
                updateSelectedCrop = { viewModel.updateUiState(selectedCrop = it) },
                launchImagePicker = {
                    imagePicker.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                shouldShowCamera = { shouldShowCamera = it },
                onSearch = { viewModel.search(context) },
                modifier = modifier
            )
        }
    } else {
        openCamera()
        CameraScreen(
            navigateUp = { shouldShowCamera = false },
            uiState = uiState,
            updateImage1 = { viewModel.updateUiState(image1 = it) },
            updateImage2 = { viewModel.updateUiState(image2 = it) },
            updateImage3 = { viewModel.updateUiState(image3 = it) },
            addImage = { viewModel.updateUiState(currentImage = uiState.currentImage + 1) },
            modifier = modifier
        )
    }
}

@Composable
fun ProblemImageScreenContent(
    uiState: ProblemImagesUiState,
    updateSelectedCrop: (Crop) -> Unit,
    launchImagePicker: () -> Unit,
    shouldShowCamera: (Boolean) -> Unit,
    onSearch: () -> Unit,
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
        CropsList(crops = uiState.crops, setSelectedCrop = { updateSelectedCrop(it) })
        Text(
            text = stringResource(id = R.string.choose_problem_image_way),
            color = greenDark
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            WayChoose(
                image = R.drawable.baseline_image_24,
                text = R.string.from_gallery,
                onItemClick = {
                    if (uiState.currentImage != 3) launchImagePicker()
                },
                modifier = Modifier.weight(1f)
            )
            WayChoose(
                image = R.drawable.baseline_camera_alt_24,
                text = R.string.from_camera,
                onItemClick = { shouldShowCamera(true) },
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
            ImageView(image = uiState.image1)
            ImageView(image = uiState.image2)
            ImageView(image = uiState.image3)

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
        uiState = ProblemImagesUiState(),
        updateSelectedCrop = { },
        launchImagePicker = { },
        shouldShowCamera = { },
        onSearch = { }
    )
}