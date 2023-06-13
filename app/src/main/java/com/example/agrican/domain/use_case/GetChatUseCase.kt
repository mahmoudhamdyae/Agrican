package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): Flow<Chat> {
//        return mainRepository.getChat()

        return flow {
            emit(
                Chat(messages = listOf(
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
            )))
        }
    }
}