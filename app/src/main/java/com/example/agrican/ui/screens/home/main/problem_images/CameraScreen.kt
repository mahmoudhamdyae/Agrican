package com.example.agrican.ui.screens.home.main.problem_images

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
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.agrican.R
import com.example.agrican.ui.theme.spacing
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
    updateImage1: (Uri) -> Unit,
    updateImage2: (Uri) -> Unit,
    updateImage3: (Uri) -> Unit,
    addImage: () -> Unit,
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
            checkCameraHardwareAndOpenCamera(context) { }
        } else {
            // Show dialog
            Toast.makeText(context, "Permission request denied", Toast.LENGTH_SHORT).show()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                checkAndRequestCameraPermission(context, permission, launcher) { }
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
        outputDirectory = outputDirectory,
        executor = cameraExecutor,
        navigateUp = navigateUp,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun CameraView(
    uiState: ProblemImagesUiState,
    updateImage1: (Uri) -> Unit,
    updateImage2: (Uri) -> Unit,
    updateImage3: (Uri) -> Unit,
    addImage: () -> Unit,
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

            IconButton(
                onClick = { navigateUp() },
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .align(Alignment.BottomCenter)
            ) {
                ImageCaptured(image = uiState.image1)
                ImageCaptured(image = uiState.image2)
                ImageCaptured(image = uiState.image3)
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = MaterialTheme.spacing.large)
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_done_24),
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            IconButton(
                onClick = {
                    if (uiState.currentImage != 3) {
                        takePhoto(
                            imageCapture = imageCapture,
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
                    imageVector = Icons.Sharp.Lens,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility_on),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}

@Composable
fun ImageCaptured(
    image: Uri?,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier.size(75.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )
    }
}

fun checkAndRequestCameraPermission(
    context: Context,
    permission: String,
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    openCamera: () -> Unit
) {
    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // Open camera because permission is already granted
        checkCameraHardwareAndOpenCamera(context, openCamera)
    } else {
        // Request a permission
        launcher.launch(permission)
    }
}

private fun checkCameraHardwareAndOpenCamera(context: Context, openCamera: () -> Unit) {
    if (checkCameraHardware(context)) {
        openCamera()
    } else {
        Toast.makeText(context, "No Camera", Toast.LENGTH_SHORT).show()
    }
}

/** Check if this device has a camera */
private fun checkCameraHardware(context: Context): Boolean {
    return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
}

private fun takePhoto(
    imageCapture: ImageCapture,
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