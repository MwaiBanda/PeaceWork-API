package com.mwaibanda.data.source.conversations

import com.mwaibanda.data.model.messaging.Conversation

interface ConversationDataSource {
   suspend fun getConversationsByUserID(userID: String): List<Conversation>
   suspend fun insertConversation(conversation: Conversation)
}