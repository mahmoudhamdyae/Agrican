package com.theflankers.agrican.ui.screens.home.main.problem_images

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.components.CropsList
import com.theflankers.agrican.ui.components.EmptyImage
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.main.problem_images.disease_capture_result.DiseaseCaptureResultDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.title
import com.theflankers.agrican.ui.theme.white

object ProblemImagesDestination: NavigationDestination {
    override val route: String = "problem_images"
    override val titleRes: Int = R.string.problem_images
}

@Composable
fun ProblemImagesScreen(
    navigateUp: () -> Unit,
    openCamera: () -> Unit,
    openScreen: (String) -> Unit,
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
        BackButtonTopBar(
            title = ProblemImagesDestination.titleRes,
            navigateUp = navigateUp,
            modifier = modifier
        ) {
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
                openScreen = openScreen,
                delImage = { viewModel.delImage(it) },
                modifier =  modifier
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
            delImage = { viewModel.delImage(it) },
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
    openScreen: (String) -> Unit,
    delImage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        Text(
            text = stringResource(id = R.string.choose_plant_type),
            style = MaterialTheme.typography.title,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        CropsList(
            isLoading = uiState.isLoading,
            crops = uiState.crops,
            setSelectedCrop = { updateSelectedCrop(it) }
        )
        Text(
            text = stringResource(id = R.string.choose_problem_image_way),
            style = MaterialTheme.typography.title,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Choose Image From Gallery
            WayChoose(
                image = R.drawable.gallery,
                text = R.string.from_gallery,
                onItemClick = {
                    if (uiState.currentImage != 3) launchImagePicker()
                },
                modifier = Modifier.weight(1f)
            )
            // Choose Image From Camera
            WayChoose(
                image = R.drawable.camera,
                text = R.string.from_camera,
                onItemClick = { shouldShowCamera(true) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    onSearch()
                    openScreen(DiseaseCaptureResultDestination.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.start_searching),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
            ImageView(
                image = uiState.image1,
                delAction = { delImage(1) }
            )
            ImageView(
                image = uiState.image2,
                delAction = { delImage(2) }
            )
            ImageView(
                image = uiState.image3,
                delAction = { delImage(3) }
            )

        }

        Box(modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()) {

            // Advices Background
            Image(
                painter = painterResource(id = R.drawable.advices),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            // Advices Label
            Text(
                text = stringResource(id = R.string.advices_label),
                color = white,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }

        // Advices
        Text(
            text = stringResource(id = R.string.advices),
            style = MaterialTheme.typography.body,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )
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
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, greenDark),
        modifier = modifier
            .padding(vertical = 16.dp)
            .clickable {
                onItemClick()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 50.dp,)
        ) {
            // Choose Icon
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
            )
            // Choose Text
            Text(
                text = stringResource(id = text),
                color = greenDark,
                style = MaterialTheme.typography.body,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ImageView(
    image: Uri?,
    delAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (image == null) {
            Surface(
                border = BorderStroke(1.dp, greenLight),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(60.dp)
            ) {
                EmptyImage(tint = greenLight, background = white)
            }
        } else {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = image)
                    .build(),
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        // Del Button
        Surface(
            shape = CircleShape,
            color = greenLight,
            border = BorderStroke(1.dp, greenLight),
            modifier = Modifier
                .padding(4.dp)
                .clickable { delAction() }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = white,
                modifier = Modifier
                    .size(16.dp)
                    .padding(2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProblemImageScreenPreview() {
    ProblemImageScreenContent(
        uiState = ProblemImagesUiState(),
        updateSelectedCrop = { },
        launchImagePicker = { },
        shouldShowCamera = { },
        onSearch = { },
        openScreen = { },
        delImage = { }
    )
}