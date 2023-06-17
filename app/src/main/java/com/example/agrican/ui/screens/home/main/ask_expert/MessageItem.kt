package com.example.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.agrican.R
import com.example.agrican.common.ext.decodeImage
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.MessageType
import com.example.agrican.ui.screens.home.main.ask_expert.playback.AndroidAudioPlayer
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import java.io.File

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
                modifier = Modifier.weight(1f)
            )
        }
        if (!isUserMe) {
            MessageItemContent(
                message = message,
                isUserMe = false,
                modifier = Modifier.weight(1f)
            )
            UserImage(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .size(MaterialTheme.spacing.dp_40)
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
                topStart = MaterialTheme.spacing.dp_20,
                topEnd = MaterialTheme.spacing.extraSmall,
                bottomEnd = MaterialTheme.spacing.dp_20,
                bottomStart = MaterialTheme.spacing.dp_20
            ) else RoundedCornerShape(
                topStart = MaterialTheme.spacing.extraSmall,
                topEnd = MaterialTheme.spacing.dp_20,
                bottomEnd = MaterialTheme.spacing.dp_20,
                bottomStart = MaterialTheme.spacing.dp_20
            )
            Surface(
                color = gray,
                shape = chatBubbleShape,
            ) {
                when(message.type) {
                    MessageType.TEXT -> {
                        TextMessage(
                            messageBody = message.body.orEmpty(),
                            modifier = Modifier.padding(MaterialTheme.spacing.medium)
                        )
                    }
                    MessageType.IMAGE -> { ImageMessage(image = message.image!!) }
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
        fontSize = MaterialTheme.spacing.sp_11,
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
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var playing by rememberSaveable { mutableStateOf(false) }
    val player by lazy { AndroidAudioPlayer(context) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(text = "01:30")

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))

        IconButton(onClick = {
            playing = !playing
            player.playFile(audioFile)
        }) {
            Icon(
                imageVector = if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
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