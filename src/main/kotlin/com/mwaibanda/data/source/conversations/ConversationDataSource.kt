package com.mwaibanda.data.source.conversations

import com.mwaibanda.data.model.conversation.Conversation
import com.mwaibanda.data.model.conversation.LastSent

interface ConversationDataSource {
   suspend fun getConversationsByUserID(userID: String): List<Conversation>
   suspend fun insertConversation(conversation: Conversation)
   suspend fun updateLastSent(conversationId: String, lastSent: LastSent)
}