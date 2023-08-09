package com.theflankers.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.background
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.decodeImage
import com.theflankers.agrican.common.utils.audio.VisualizerData
import com.theflankers.agrican.common.utils.millisecondsToTimeString
import com.theflankers.agrican.domain.model.AudioFile
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.MessageType
import com.theflankers.agrican.ui.theme.black
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.textGray
import com.theflankers.agrican.ui.theme.white

@Composable
fun MessageItem(
    message: Message,
    isUserMe: Boolean,
    visualizerData: VisualizerData,
    uiState: ChatUiState,
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
                uiState = uiState,
                modifier = Modifier.weight(1f)
            )
        }
        if (!isUserMe) {
            MessageItemContent(
                message = message,
                isUserMe = false,
                visualizerData = visualizerData,
                onEvent = onEvent,
                uiState = uiState,
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
    uiState: ChatUiState,
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
                            audioFile = message.audioFile,
                            modifier = Modifier.padding(16.dp),
                            uiState = uiState,
                            visualizerData = visualizerData,
                            onEvent = onEvent,
                            isSelected = uiState.selectedAudio.audioId == message.audioFile.audioId
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
    uiState: ChatUiState,
    audioFile: AudioFile,
    visualizerData: VisualizerData,
    onEvent: (AudioPlayerEvent) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Time String
        Text(
            text = if (uiState.isPlaying && isSelected) millisecondsToTimeString(uiState.currentTime)
                else millisecondsToTimeString(audioFile.duration),
            color = textGray,
            fontSize = 12.sp
        )

        StackedBarVisualizer(
            modifier = Modifier
                .width(110.dp)
                .height(height = 32.dp)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            barCount = 32,
            barColors = listOf(greenDark, greenDark, greenDark),
            stackBarBackgroundColor = Color(0xFFC7C4C4),
//            stackBarBackgroundColor = textGray,
            data = if (isSelected) visualizerData else VisualizerData()
        )

        IconButton(onClick = {
            if (uiState.isPlaying) {
                // Pause Audio
                onEvent(AudioPlayerEvent.Pause)
            } else {
                // Init Audio
                onEvent(AudioPlayerEvent.InitAudio(
                    audio = audioFile,
                    context = context,
                    onAudioInitialized = { onEvent(AudioPlayerEvent.Play) }))
            }
        }) {
            Icon(
                imageVector = if (uiState.isPlaying && isSelected) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                tint = white,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(greenDark)
                    .padding(4.dp)
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