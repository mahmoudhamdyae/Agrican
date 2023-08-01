package com.theflankers.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.Chat
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.MessageType
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.theme.greenDark
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

    BackButtonTopBar(
        title = ChatDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
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
            scrollState = scrollState
        )
    }
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Finish Chat Button
        Button(
            onClick = { navigateUp() },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = R.string.finish_chat),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 24.dp)
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
                .padding(8.dp)
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
        items(items = messages, key = { it.messageId }) {
            MessageItem(
                message = it,
                isUserMe = it.userId == userId,
            )
        }
    }
}