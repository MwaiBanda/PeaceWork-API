package com.mwaibanda.controllers

import com.mwaibanda.data.model.conversation.Conversation
import com.mwaibanda.data.model.conversation.LastSent
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
    suspend fun deleteConversation(conversationId: String) {
        conversationDataSource.deleteConversation(conversationId)
    }

    suspend fun updateLastSent(conversationId: String, lastSent: LastSent) {
        conversationDataSource.updateLastSent(conversationId, lastSent)
    }
}