package com.example.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.MessageType
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import kotlinx.coroutines.launch
import java.io.File

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
        sendMessage = {
            viewModel.sendMessage(messageBody = it, messageType = MessageType.TEXT)
            scope.launch {
                scrollState.animateScrollToItem(uiState.chat.messages.size - 1)
            }
        },
        sendImage = {
            viewModel.sendMessage(image = it, messageType = MessageType.IMAGE)
            scope.launch {
                scrollState.animateScrollToItem(uiState.chat.messages.size - 1)
            }
        },
        sendFile = {
            viewModel.sendMessage(file = it, messageType = MessageType.VOICE)
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
    sendMessage: (String) -> Unit,
    sendImage: (String?) -> Unit,
    sendFile: (File?) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(MaterialTheme.spacing.small)) {
        // Finish Chat Button
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