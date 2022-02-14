package com.mwaibanda.data.source.messages

import com.mwaibanda.data.model.messaging.Conversation
import com.mwaibanda.data.model.messaging.Member
import com.mwaibanda.data.model.messaging.Message
import org.litote.kmongo.contains
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.match

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