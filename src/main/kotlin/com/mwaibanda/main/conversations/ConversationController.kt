package com.mwaibanda.main.conversations

import com.mwaibanda.data.model.messaging.Conversation
import com.mwaibanda.data.source.conversations.ConversationDataSource

class ConversationController(
    private val conversationDataSource: ConversationDataSource
) {
    suspend fun getConversationsByUserID(userID: String): List<Conversation> {
        return conversationDataSource.getConversationsByUserID(userID)
    }
    suspend fun postConversation(conversation: Conversation) {
       conversationDataSource.insertConversation(conversation)
    }
}