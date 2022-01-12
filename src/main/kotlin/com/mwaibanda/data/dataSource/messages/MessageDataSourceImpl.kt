package com.mwaibanda.data.dataSource.messages

import com.mwaibanda.data.model.messages.Conversation
import com.mwaibanda.data.model.messages.Message
import org.litote.kmongo.contains
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.match

class MessageDataSourceImpl(
    private val db: CoroutineDatabase
): MessageDataSource {
    private val conversations = db.getCollection<Conversation>()
    private val messages = db.getCollection<Message>()

    override suspend fun getAllMessagesForConversation(conversationId: String): List<Message> {
        return messages.find(Message::conversationId eq conversationId)
            .descendingSort(Message::timestamp)
            .toList()
    }

    override suspend fun getAllConversations(userId: String): List<Conversation> {
        return conversations.find(match(Conversation::participants contains userId))
            .descendingSort(Conversation::timestamp)
            .toList()
    }

    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }

    override suspend fun insertConversation(conversation: Conversation) {
        conversations.insertOne(conversation)
    }
}