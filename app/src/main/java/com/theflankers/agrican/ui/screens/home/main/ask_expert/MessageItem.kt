package com.theflankers.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.decodeImage
import com.theflankers.agrican.common.utils.audio.VisualizerData
import com.theflankers.agrican.common.utils.millisecondsToTimeString
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.MessageType
import com.theflankers.agrican.ui.theme.black
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.textGray
import com.theflankers.agrican.ui.theme.white
import java.io.File

@Composable
fun MessageItem(
    message: Message,
    isUserMe: Boolean,
    visualizerData: VisualizerData,
    currentTime: Int,
    isPlaying: Boolean,
    onEvent: (AudioPlayerEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = if (isUserMe) Arrangement.Start else Arrangement.End,
        modifier = modifier.padding(8.dp)
    ) {
        if (isUserMe) {
            MessageItemContent(
                message = message,
                isUserMe = true,
                visualizerData = visualizerData,
                onEvent = onEvent,
                currentTime = currentTime,
                isPlaying = isPlaying,
                modifier = Modifier.weight(1f)
            )
        }
        if (!isUserMe) {
            MessageItemContent(
                message = message,
                isUserMe = false,
                visualizerData = visualizerData,
                onEvent = onEvent,
                currentTime = currentTime,
                isPlaying = isPlaying,
                modifier = Modifier.weight(1f)
            )
            UserImage(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(40.dp)
                    .align(Alignment.Top)
            )
        }
    }
}

@Composable
fun MessageItemContent(
    message: Message,
    isUserMe: Boolean,
    visualizerData: VisualizerData,
    currentTime: Int,
    isPlaying: Boolean,
    onEvent: (AudioPlayerEvent) -> Unit,
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
                color = Color(0xfff2f2f2),
                shape = chatBubbleShape,
            ) {
                when(message.type) {
                    MessageType.TEXT -> {
                        TextMessage(
                            messageBody = message.body.orEmpty(),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    MessageType.IMAGE -> { ImageMessage(image = message.image!!) }
                    MessageType.VOICE -> {
                        VoiceMessage(
                            audioFile = message.file!!,
                            modifier = Modifier.padding(16.dp),
                            visualizerData = visualizerData,
                            onEvent = onEvent,
                            currentTime = currentTime,
                            isPlaying = isPlaying
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
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = black,
        modifier = modifier,
    )
}

@Composable
fun ImageMessage(
    image: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = image.decodeImage(),
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.loading_img),
        error =  painterResource(id = R.drawable.ic_broken_image),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

@Composable
fun VoiceMessage(
    audioFile: File,
    visualizerData: VisualizerData,
    currentTime: Int,
    isPlaying: Boolean,
    onEvent: (AudioPlayerEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var isPlayed by rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Time String
        Text(
            text = if (isPlaying) millisecondsToTimeString(currentTime) else "audioFile",
            color = textGray,
            fontSize = 12.sp
        )

        StackedBarVisualizer(
            modifier = Modifier
                .width(110.dp)
                .height(height = 32.dp)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            shape = MaterialTheme.shapes.large,
            barCount = 32,
            barColors = listOf(
                Color(0xFF1BEBE9),
                Color(0xFF39AFEA),
                Color(0xFF0291D8)
            ),
            stackBarBackgroundColor = if (isSystemInDarkTheme()) black else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f),
            data = visualizerData
        )

        IconButton(onClick = {
            if (isPlayed) {
                if (isPlaying) {
                    // Pause Audio
                    onEvent(AudioPlayerEvent.Pause)
                } else {
                    // Resume Audio
                    onEvent(AudioPlayerEvent.Play)
                }
            } else {
                // First Play
                // Init Audio
                onEvent(AudioPlayerEvent.InitAudio(
                    audio = audioFile.toUri(),
                    context = context,
                    onAudioInitialized = { onEvent(AudioPlayerEvent.Play) }))
                isPlayed = true
            }
        }) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                tint = white,
                modifier = Modifier.clip(CircleShape).background(greenDark).padding(4.dp)
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