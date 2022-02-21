package com.mwaibanda.data.source.conversations

import com.mwaibanda.data.model.conversation.Conversation
import com.mwaibanda.data.model.conversation.LastSent
import com.mwaibanda.data.model.conversation.Participant
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class ConversationDataSourceImpl(
    private val database: CoroutineDatabase
): ConversationDataSource {
    private val conversationCollection = database.getCollection<Conversation>()

    override suspend fun getConversationsByUserID(userID: String): List<Conversation> {
        return conversationCollection.find( Conversation::participants / Participant::userId eq userID).toList()
    }

    override suspend fun insertConversation(conversation: Conversation) {
        conversationCollection.insertOne(conversation)
    }

    override suspend fun updateLastSent(conversationId: String, lastSent: LastSent) {
        conversationCollection.updateOne(Conversation::id eq conversationId, set(
            Conversation::lastSent / LastSent::userId setTo lastSent.userId,
            Conversation::lastSent / LastSent::message setTo lastSent.message,
            Conversation::lastSent / LastSent::lastSentDate setTo lastSent.lastSentDate,
            Conversation::lastSent / LastSent::isSeen setTo lastSent.isSeen,
            )
        )
    }
}