package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Message

class GetChatUseCase {

    suspend operator fun invoke(): Chat {
        return Chat(messages = listOf(
            Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "1"),
            Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "2"),
            Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "3"),
            Message("text2", "2", "4"),
            Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "5"),
            Message("أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟", "1", "6"),
            Message("text2", "2", "7"),
            Message("text2", "2", "8"),
        ))
    }
}