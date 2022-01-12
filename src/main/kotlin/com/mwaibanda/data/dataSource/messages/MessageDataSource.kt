package com.mwaibanda.data.dataSource.messages

import com.mwaibanda.data.model.messages.Conversation
import com.mwaibanda.data.model.messages.Message

interface MessageDataSource {
    suspend fun getAllMessagesForConversation(conversationId: String): List<Message>
    suspend fun getAllConversations(userId: String): List<Conversation>
    suspend fun insertMessage(message: Message)
    suspend fun insertConversation(conversation: Conversation)
}