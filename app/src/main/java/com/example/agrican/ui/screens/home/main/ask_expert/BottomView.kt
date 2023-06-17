package com.example.agrican.ui.screens.home.main.ask_expert

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.agrican.R
import com.example.agrican.common.ext.encodeImage
import com.example.agrican.ui.screens.home.main.ask_expert.record.AndroidAudioRecorder
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.white
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomView(
    sendMessage: (String) -> Unit,
    sendImage: (String?) -> Unit,
    sendFile: (File?) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var message by rememberSaveable { mutableStateOf("") }

    // Camera Variables
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.agrican" + ".provider", file
    )
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        val base64Image = uri.encodeImage(context)
        sendImage(base64Image)
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    // Image Launcher
    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { imageUri ->
            val base64Image = imageUri.encodeImage(context)
            sendImage(base64Image)
        }

    // Voice Recorder Variables
    val recorder by lazy { AndroidAudioRecorder(context) }
    var audioFile: File? = null
    var recording by rememberSaveable { mutableStateOf(false) }

    val mediaPlayer = MediaPlayer.create(context, R.raw.record_audio)

    Surface(
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            // Send Button
            SendButton(
                icon = R.drawable.send,
                messageBody = message,
                onItemClick = {
                    sendMessage(it)
                    message = ""
                }
            )

            // Message Text Field
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                shape = RoundedCornerShape(MaterialTheme.spacing.large),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = greenLight,),
                modifier = Modifier.weight(1f)
            )

            // Record Voice Button
            ChatOutlinedButton(
                icon = if (recording) R.drawable.baseline_stop_24
                else R.drawable.mic,
                onItemClick = {
                    if (recording) {
                        // Stop Recording
                        recorder.stop()
                        recording = false
                        // Start Tick Sound
                        mediaPlayer.start()
                        sendFile(audioFile)
                    } else {
                        // Check Permission
                        if (checkRecordAudioPermission(context)) {
                            // Start Tick Sound
                            mediaPlayer.start()
                            // Start Recording
                            File(context.cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }
                            recording = true
                        }
                    }
                })

            // Select Image from Galley Button
            ChatOutlinedButton(icon = R.drawable.link, onItemClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            })

            // Open Camera Button
            ChatOutlinedButton(icon = R.drawable.camera_chat, onItemClick = {
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    // Request a permission
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            })
        }
    }
}

@Composable
fun ChatOutlinedButton(
    icon: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(MaterialTheme.spacing.dp_1, gray),
        shape = CircleShape,
        modifier = modifier
    ) {
        IconButton(onClick = onItemClick) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = greenLight
            )
        }
    }
}

@Composable
fun SendButton(
    icon: Int,
    messageBody: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onItemClick(messageBody) },
        colors = IconButtonDefaults.iconButtonColors(containerColor = greenLight),
        modifier = modifier
            .clip(CircleShape)
            .background(greenLight)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = white,
        )
    }
}

fun checkRecordAudioPermission(context: Context): Boolean {
    return if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
        true
    } else {
        requestRecordAudioPermission(context)
        false
    }
}

fun requestRecordAudioPermission(context: Context) {
    ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
}