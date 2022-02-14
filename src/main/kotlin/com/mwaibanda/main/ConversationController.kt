package com.mwaibanda.main

import com.mwaibanda.data.model.conversation.Conversation
import com.mwaibanda.data.model.conversation.LastSent
import com.mwaibanda.data.source.conversations.ConversationDataSource

class ConversationController(
    private val conversationDataSource: ConversationDataSource
) {
    suspend fun getConversationsByUserID(userID: String): List<Conversation> {
        return conversationDataSource.getConversationsByUserID(userID)
    }

    suspend fun updateConversation(conversation: Conversation) {
        conversationDataSource.insertConversation(conversation)
    }

    suspend fun updateLastSent(conversationId: String, lastSent: LastSent) {
        conversationDataSource.updateLastSent(conversationId, lastSent)
    }
}