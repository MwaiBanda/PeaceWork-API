package com.mwaibanda.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    @BsonId
    val id: String = ObjectId().toString(),
    val text: String,
    val userId: String,
    val timestamp: Long,
    val date: String,
    val conversationId: String
)
