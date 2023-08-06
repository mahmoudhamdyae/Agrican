package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): Flow<List<Message>> {
        return mainRepository.getChat()

//        return flow {
//            emit(
//                Chat(messages = listOf(
//                Message(
//                    body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
//                    userId = "1",
//                    messageId = "1",
//                    type = MessageType.TEXT
//                ),
//                Message(
//                    body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
//                    userId = "1",
//                    messageId = "2",
//                    type = MessageType.TEXT
//                ),
//                Message(
//                    body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
//                    userId = "1",
//                    messageId = "3",
//                    type = MessageType.TEXT
//                ),
//                Message(body = "text2", userId = "2", messageId = "4"),
//                Message(
//                    body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
//                    userId = "1",
//                    messageId = "5",
//                    type = MessageType.TEXT
//                ),
//                Message(
//                    body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
//                    userId = "1",
//                    messageId = "6",
//                    type = MessageType.TEXT
//                ),
//                Message(
//                    body = "text2",
//                    userId = "2",
//                    messageId = "7",
//                    type = MessageType.TEXT
//                ),
//                Message(
//                    body = "text2",
//                    userId = "2",
//                    messageId = "8",
//                    type = MessageType.TEXT
//                ),
//            )))
//        }
    }
}