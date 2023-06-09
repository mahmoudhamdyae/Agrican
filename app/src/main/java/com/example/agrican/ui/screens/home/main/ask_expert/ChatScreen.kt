package com.example.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import kotlinx.coroutines.launch

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

    // todo: get from viewmodel
    val chat = Chat(id = "1", messages = listOf(
        Message("text1", "1", "1"),
        Message("text2", "2", "2")
    ))

    val userId = "2"

    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    ChatScreenContent(
        navigateUp = navigateUp,
        userId = userId,
        chat = chat,
        sendMessage = {
            viewModel.sendMessage(it)
            scope.launch {
                scrollState.animateScrollToItem(0)
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
    sendMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {
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
    IconButton(
        onClick = { },
        colors = IconButtonDefaults.iconButtonColors(containerColor = greenLight),
        modifier = modifier
            .clip(CircleShape)
            .background(greenLight)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_visibility_on),
            contentDescription = null,
            tint = Color.White,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomView(
    sendMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var message by rememberSaveable { mutableStateOf("") }

    Surface(
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            ChatOutlinedButton(icon = R.drawable.ic_visibility_on, onItemClick = { /*TODO*/ })
            ChatOutlinedButton(icon = R.drawable.ic_visibility_on, onItemClick = { /*TODO*/ })
            ChatOutlinedButton(icon = R.drawable.ic_visibility_on, onItemClick = { /*TODO*/ })

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                shape = RoundedCornerShape(MaterialTheme.spacing.large),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = greenLight,),
                modifier = Modifier.weight(1f)
            )

            ChatButton(
                icon = R.drawable.ic_visibility_on,
                messageBody = message,
                onItemClick = sendMessage
            )
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
fun ChatButton(
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
            tint = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    val chat = Chat(id = "1", messages = listOf(
        Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "1"),
        Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "2"),
        Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "3"),
        Message("text2", "2", "4"),
        Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "5"),
        Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "6"),
        Message("text2", "2", "7"),
        Message("text2", "2", "8"),
    ))
    ChatScreenContent(userId = "2", chat = chat, sendMessage = { }, navigateUp = { })
}