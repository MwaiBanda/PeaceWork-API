package com.mwaibanda.data.source.messages

import com.mwaibanda.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MessageDataSourceImpl(
    private val db: CoroutineDatabase
): MessageDataSource {
    private val messages = db.getCollection<Message>()

    override suspend fun getAllMessagesForConversation(conversationId: String): List<Message> {
        return messages.find(Message::conversationId eq conversationId)
            .ascendingSort(Message::timestamp)
            .toList()
    }
    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }


}