package com.theflankers.agrican.ui.screens.home.main.ask_expert

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.encodeImage
import com.theflankers.agrican.domain.model.AudioFile
import com.theflankers.agrican.ui.screens.home.main.ask_expert.record.AndroidAudioRecorder
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.white
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun BottomView(
    sendMessage: (String) -> Unit,
    sendImage: (String?) -> Unit,
    sendFile: (AudioFile) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var message by rememberSaveable { mutableStateOf("") }

    // Camera Variables
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.theflankers.agrican" + ".provider", file
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
        shadowElevation = 32.dp,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, Color(0x7AC6C6C6)),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(8.dp)
                .height(35.dp)
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
            Surface(
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, greenLight),
                modifier = Modifier.weight(1f)
            ) {
                BasicTextField(
                    value = message,
                    onValueChange = { message = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .height(35.dp)
                                .fillMaxWidth()
                                .padding(start = 10.dp, top = 10.dp),
                        ) {
                            innerTextField()
                        }
                    }
                )
            }

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

                        // Get Duration of Recorder Message
                        val mediaMetadataRetriever = MediaMetadataRetriever()
                        mediaMetadataRetriever.setDataSource(audioFile?.absolutePath)
                        val milliseconds = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                        // Send Voice Message
                        sendFile(AudioFile(audioFile?.toUri() ?: Uri.EMPTY, milliseconds?.toInt() ?: 0))
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
        border = BorderStroke(1.dp, gray),
        shape = CircleShape,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = greenLight,
            modifier = Modifier
                .padding(8.dp)
                .size(16.dp)
                .clickable { onItemClick() }
        )
    }
}

@Composable
fun SendButton(
    icon: Int,
    messageBody: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = CircleShape,
        color = greenLight,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = white,
            modifier = Modifier
                .padding(8.dp)
                .size(16.dp)
                .clickable { onItemClick(messageBody) }
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