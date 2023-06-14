package com.example.agrican.ui.screens.home.main.ask_expert

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.MessageType
import com.example.agrican.ui.navigation.NavigationDestination
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

    // todo: UserId
    val userId = "2"

    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

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
        sendImage = { uri, messageType ->
            viewModel.sendMessage(image = uri, messageType = messageType)
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
    sendImage: (Uri, MessageType) -> Unit,
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
            sendImage = sendImage,
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
            TextMessage(
                message = message,
                isUserMe = true,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
        }
        if (!isUserMe) {
            UserImage(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .align(Alignment.Top)
            )
            TextMessage(
                message = message,
                isUserMe = false,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            )
        }
    }
}

@Composable
fun TextMessage(
    message: Message,
    isUserMe: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val chatModifier = if (isUserMe)  Modifier.align(Alignment.End) else Modifier

        Column(modifier = chatModifier) {
            val chatBubbleShape = if (isUserMe) RoundedCornerShape(
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
                shape = chatBubbleShape
            ) {
                Text(
                    text = message.body,
                    style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
                    modifier = Modifier.padding(16.dp),
                )
            }
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
    sendImage: (Uri, MessageType) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var message by rememberSaveable { mutableStateOf("") }

    val file = context.createImageFile()
    val cameraUri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.agrican" + ".provider", file
    )
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            sendImage(cameraUri, MessageType.IMAGE)
        }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(cameraUri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) { sendImage(uri, MessageType.IMAGE) }
        }

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

            ChatOutlinedButton(icon = Icons.Outlined.RecordVoiceOver, onItemClick = {
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
                    cameraLauncher.launch(cameraUri)
                } else {
                    // Request a permission
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            })
        }
    }
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
            messageId = "1"
        ),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "2"
        ),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "3"
        ),
        Message(body = "text2", userId = "2", messageId = "4"),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "5"
        ),
        Message(
            body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
            userId = "1",
            messageId = "6"
        ),
        Message(body = "text2", userId = "2", messageId = "7"),
        Message(body = "text2", userId = "2", messageId = "8"),
    ))
    ChatScreenContent(userId = "2", chat = chat, sendMessage = { _, _ -> }, sendImage = { _, _ -> }, navigateUp = { })
}