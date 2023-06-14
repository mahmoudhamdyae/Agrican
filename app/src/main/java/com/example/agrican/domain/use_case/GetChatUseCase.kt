package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.MessageType
import com.example.agrican.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
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
//                    file = File("/data/user/0/com.example.agrican/cache/audio.mp3"),
                    file = File("/picker/0/com.android.providers.media.photopicker/media/1000137870"),
                    type = MessageType.VOICE
                )
            )))
        }
    }
}