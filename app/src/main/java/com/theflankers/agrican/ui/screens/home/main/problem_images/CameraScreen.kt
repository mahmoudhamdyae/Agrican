package com.theflankers.agrican.ui.screens.home.main.problem_images

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.theflankers.agrican.R
import com.theflankers.agrican.ui.theme.black
import com.theflankers.agrican.ui.theme.white
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(
    navigateUp: () -> Unit,
    uiState: ProblemImagesUiState,
    updateImage1: (Uri?) -> Unit,
    updateImage2: (Uri?) -> Unit,
    updateImage3: (Uri?) -> Unit,
    addImage: () -> Unit,
    delImage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val outputDirectory: File by remember {
        mutableStateOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
    }
    var cameraExecutor: ExecutorService? by remember { mutableStateOf(null) }

    val context = LocalContext.current

    val permission = Manifest.permission.CAMERA
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Open camera
            checkCameraHardwareAndOpenCamera(context)
        } else {
            Toast.makeText(context, "Permission request denied", Toast.LENGTH_SHORT).show()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                checkAndRequestCameraPermission(context, permission, launcher)
                cameraExecutor = Executors.newSingleThreadExecutor()
            } else if (event == Lifecycle.Event.ON_STOP) {
                cameraExecutor?.shutdown()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    CameraView(
        uiState = uiState,
        updateImage1 = updateImage1,
        updateImage2 = updateImage2,
        updateImage3 = updateImage3,
        addImage = addImage,
        delImage = delImage,
        outputDirectory = outputDirectory,
        executor = cameraExecutor,
        navigateUp = navigateUp,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun CameraView(
    uiState: ProblemImagesUiState,
    updateImage1: (Uri?) -> Unit,
    updateImage2: (Uri?) -> Unit,
    updateImage3: (Uri?) -> Unit,
    addImage: () -> Unit,
    delImage: (Int) -> Unit,
    outputDirectory: File,
    executor: Executor?,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    var flashOn by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.weight(1f)) {
            AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // Back Button
                IconButton(
                    onClick = { navigateUp() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                        contentDescription = null,
                        tint = black,
                    )
                }

                // Camera Border
                Icon(
                    painter = painterResource(id = R.drawable.camera_border),
                    contentDescription = null,
                    tint = white,
                    modifier = Modifier.padding(horizontal = 16.dp).weight(1f)
                )

                // Images Captured
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    ImageCaptured(
                        image = uiState.image1,
                        delAction = { delImage(1) }
                    )
                    ImageCaptured(
                        image = uiState.image2,
                        delAction = { delImage(2) }
                    )
                    ImageCaptured(
                        image = uiState.image3,
                        delAction = { delImage(3) }
                    )
                }
            }

        }

        // Bottom Icons
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(black)
                .padding(vertical = 32.dp, horizontal = 16.dp)
        ) {
            // Navigate Back and Save Images
            IconButton(onClick = navigateUp) {
                Icon(
                    painter = painterResource(id = R.drawable.done),
                    contentDescription = null,
                    tint = white
                )
            }

            // Take Picture Button
            IconButton(
                onClick = {
                    if (uiState.currentImage != 3) {
                        takePhoto(
                            imageCapture = imageCapture,
                            flashOn = flashOn,
                            outputDirectory = outputDirectory,
                            executor = executor,
                            onImageCaptured = {
                                when (uiState.currentImage) {
                                    0 -> { updateImage1(it) }
                                    1 -> { updateImage2(it) }
                                    2 -> { updateImage3(it) }
                                    else  -> {}
                                }
                                addImage()
                            },
                        )
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lens),
                    contentDescription = "Take picture",
                    tint = white
                )
            }

            // Set Flash on and off
            IconButton(onClick = { flashOn = !flashOn }) {
                Icon(
                    painter = painterResource(id =
                        if (flashOn) R.drawable.baseline_flash_on_24
                        else R.drawable.flash_off
                    ),
                    contentDescription = null,
                    tint = white
                )
            }
        }
    }
}

@Composable
fun ImageCaptured(
    image: Uri?,
    delAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(2.dp, white),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.size(75.dp)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = image ?: R.drawable.gray_placeholder)
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
            )

            // Del Button
            Surface(
                shape = CircleShape,
                color = Color(0xffc9c9c9),
                modifier = Modifier.padding(6.dp).clickable { delAction() }.align(Alignment.TopEnd)
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
}

fun checkAndRequestCameraPermission(
    context: Context,
    permission: String,
    launcher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // Open camera because permission is already granted
        checkCameraHardwareAndOpenCamera(context)
    } else {
        // Request a permission
        launcher.launch(permission)
    }
}

private fun checkCameraHardwareAndOpenCamera(context: Context) {
    if (!checkCameraHardware(context)) {
        Toast.makeText(context, "No Camera", Toast.LENGTH_SHORT).show()
    }
}

/** Check if this device has a camera */
private fun checkCameraHardware(context: Context): Boolean {
    return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
}

private fun takePhoto(
    imageCapture: ImageCapture,
    flashOn: Boolean,
    outputDirectory: File,
    executor: Executor?,
    onImageCaptured: (Uri) -> Unit,
) {

    val photoFile = File(
        outputDirectory,
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
            .format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.flashMode = if (flashOn) FLASH_MODE_ON else FLASH_MODE_OFF

    imageCapture.takePicture(outputOptions, executor!!, object: ImageCapture.OnImageSavedCallback {
        override fun onError(exception: ImageCaptureException) {
        }

        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val savedUri = Uri.fromFile(photoFile)
            onImageCaptured(savedUri)
        }
    })
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}