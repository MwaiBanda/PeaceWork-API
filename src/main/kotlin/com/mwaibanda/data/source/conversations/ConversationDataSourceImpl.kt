package com.mwaibanda.data.source.conversations

import com.mwaibanda.data.model.messaging.Conversation
import com.mwaibanda.data.model.messaging.Member
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class ConversationDataSourceImpl(
    private val database: CoroutineDatabase
): ConversationDataSource {
    private val conversationCollection = database.getCollection<Conversation>()

    override suspend fun getConversationsByUserID(userID: String): List<Conversation> {
        return conversationCollection.find( Conversation::participants / Member::id eq userID ).toList()
    }

    override suspend fun insertConversation(conversation: Conversation) {
        conversationCollection.insertOne(conversation)
    }
}