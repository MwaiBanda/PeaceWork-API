package com.mwaibanda.data.model.messages

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    @BsonId
    val id: String = ObjectId().toString(),
    val text: String,
    val username: String,
    val timestamp: Long,
    val sender: String,
    val recipient: String,
    val conversationId: String
)
