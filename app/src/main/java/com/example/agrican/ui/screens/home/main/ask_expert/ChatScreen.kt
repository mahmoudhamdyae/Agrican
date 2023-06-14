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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.RecordVoiceOver
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.agrican.R
import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.MessageType
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.ask_expert.playback.AndroidAudioPlayer
import com.example.agrican.ui.screens.home.main.ask_expert.record.AndroidAudioRecorder
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

object ChatDestination: NavigationDestination {
    override val route: String = "chat"
    override val titleRes: Int = R.string.ask_expert
}

@Composable
fun ChatScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val userId = uiState.currentUser.userId

    val scope = rememberCoroutineScope()
    val scrollState =
        rememberLazyListState(initialFirstVisibleItemIndex = uiState.chat.messages.size - 1)

    ChatScreenContent(
        navigateUp = navigateUp,
        userId = userId,
        chat = uiState.chat,
        sendMessage = { messageBody, messageType ->
            viewModel.sendMessage(messageBody = messageBody, messageType = messageType)
            scope.launch {
                scrollState.animateScrollToItem(uiState.chat.messages.size - 1)
            }
        },
        sendFile = { file ->
            viewModel.sendMessage(file = file, messageType = MessageType.IMAGE)
            scope.launch {
                scrollState.animateScrollToItem(uiState.chat.messages.size - 1)
            }
        },
        scrollState = scrollState,
        modifier = modifier
    )
}

@Composable
fun ChatScreenContent(
    navigateUp: () -> Unit,
    userId: String,
    chat: Chat,
    sendMessage: (String, MessageType) -> Unit,
    sendFile: (File?) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(MaterialTheme.spacing.small)) {
        Button(
            onClick = { navigateUp() },
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.finish_chat),
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
            )
        }

        ChatMessages(
            userId = userId,
            messages = chat.messages,
            modifier = Modifier.weight(1f),
            scrollState = scrollState
        )

        BottomView(
            sendMessage = sendMessage,
            sendFile = sendFile,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small)
        )
    }
}

@Composable
fun ChatMessages(
    userId: String,
    messages: List<Message>,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = scrollState,
        modifier = modifier
    ) {
        items(count = messages.size) {
            MessageItem(
                message = messages[it],
                isUserMe = messages[it].userId == userId,
            )
        }
    }
}

@Composable
fun MessageItem(
    message: Message,
    isUserMe: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = if (isUserMe) Arrangement.Start else Arrangement.End,
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        if (isUserMe) {
            MessageItemContent(
                message = message,
                isUserMe = true,
                modifier = Modifier
//                    .padding(start = 16.dp)
                    .weight(1f)
            )
        }
        if (!isUserMe) {
            MessageItemContent(
                message = message,
                isUserMe = false,
                modifier = Modifier
//                    .padding(end = 16.dp)
                    .weight(1f)
            )
            UserImage(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .size(42.dp)
                    .align(Alignment.Top)
            )
        }
    }
}

@Composable
fun MessageItemContent(
    message: Message,
    isUserMe: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val chatModifier = if (!isUserMe)  Modifier.align(Alignment.End) else Modifier

        Column(modifier = chatModifier) {
            val chatBubbleShape = if (!isUserMe) RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 4.dp,
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            ) else RoundedCornerShape(
                topStart = 4.dp,
                topEnd = 20.dp,
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            )
            Surface(
                color = gray,
                shape = chatBubbleShape,
            ) {
                when(message.type) {
                    MessageType.TEXT -> {
                        TextMessage(
                            messageBody = message.body,
                            modifier = Modifier.padding(MaterialTheme.spacing.medium)
                        )
                    }
                    MessageType.IMAGE -> { ImageMessage(image = message.file!!) }
                    MessageType.VOICE -> {
                        VoiceMessage(
                            audioFile = message.file!!,
                            modifier = Modifier.padding(MaterialTheme.spacing.medium)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextMessage(
    messageBody: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = messageBody,
        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
        modifier = modifier,
    )
}

@Composable
fun ImageMessage(
    image: File,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = image,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.loading_img),
        error =  painterResource(id = R.drawable.ic_broken_image),
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}

@Composable
fun VoiceMessage(
    audioFile: File,
    modifier: Modifier = Modifier
) {
    var playing by rememberSaveable { mutableStateOf(false) }

    val mContext = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(text = "01:30")

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))

        IconButton(onClick = {
            playing = !playing
        }) {
            Icon(
                imageVector = if (playing) Icons.Default.Pause else Icons.Default.PlayArrow
                ,
                contentDescription = null
            )
        }
    }
}

@Composable
fun UserImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(greenDark)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomView(
    sendMessage: (String, MessageType) -> Unit,
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
        uri.path?.let { path -> sendFile(File(path)) }
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
            imageUri?.path?.let { path -> sendFile(File(path)) }
        }

    // Voice Recorder Variables
    val recorder by lazy { AndroidAudioRecorder(context) }
    val player by lazy { AndroidAudioPlayer(context) }
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
            ChatButton(
                icon = R.drawable.ic_visibility_on,
                messageBody = message,
                onItemClick = sendMessage
            )

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                shape = RoundedCornerShape(MaterialTheme.spacing.large),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = greenLight,),
                modifier = Modifier.weight(1f)
            )

            ChatOutlinedButton(
                icon = if (recording) Icons.Outlined.RecordVoiceOver
                    else Icons.Outlined.RecordVoiceOver,
                onItemClick = {
                    if (recording) {
                        mediaPlayer.start()
                        // Stop Recording
                        recorder.stop()
                        recording = false
                        sendFile(audioFile)
                    } else {
                        if (checkRecordAudioPermission(context)) {
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

            ChatOutlinedButton(icon = Icons.Outlined.Link, onItemClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            })

            ChatOutlinedButton(icon = Icons.Outlined.CameraAlt, onItemClick = {
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

@Composable
fun ChatOutlinedButton(
    icon: ImageVector,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(1.dp, gray),
        shape = CircleShape,
        modifier = modifier
    ) {
        IconButton(onClick = onItemClick) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = greenLight
            )
        }
    }
}

@Composable
fun ChatButton(
    icon: Int,
    messageBody: String,
    onItemClick: (String, MessageType) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onItemClick(messageBody, MessageType.TEXT) },
        colors = IconButtonDefaults.iconButtonColors(containerColor = greenLight),
        modifier = modifier
            .clip(CircleShape)
            .background(greenLight)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    val chat = Chat(chatId = "1", messages = listOf(
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "1",
            type = MessageType.TEXT
        ),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "2",
            type = MessageType.TEXT
        ),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "3",
            type = MessageType.TEXT
        ),
        Message(body = "text2", userId = "2", messageId = "4"),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "5",
            type = MessageType.TEXT
        ),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "6",
            type = MessageType.TEXT
        ),
        Message(
            body = "text2",
            userId = "2",
            messageId = "7",
            type = MessageType.TEXT
        ),
        Message(
            body = "text2",
            userId = "2",
            messageId = "8",
            type = MessageType.TEXT
        ),
        Message(
            userId = "2",
            messageId = "9",
            file = File("/picker/0/com.android.providers.media.photopicker/media/1000137870"),
            type = MessageType.IMAGE

        ),
        Message(
            userId = "2",
            messageId = "9",
            file = File("/picker/0/com.android.providers.media.photopicker/media/1000137870"),
            type = MessageType.VOICE
        )
    ))
    ChatScreenContent({ }, "2", chat, { _, _ -> }, { })
}