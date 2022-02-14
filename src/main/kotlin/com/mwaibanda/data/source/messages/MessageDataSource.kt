package com.mwaibanda.data.source.messages

import com.mwaibanda.data.model.messaging.Conversation
import com.mwaibanda.data.model.messaging.Message

interface MessageDataSource {
    suspend fun getAllMessagesForConversation(conversationId: String): List<Message>
    suspend fun insertMessage(message: Message)
}