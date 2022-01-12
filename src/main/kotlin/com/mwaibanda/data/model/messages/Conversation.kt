package com.mwaibanda.data.model.messages

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Conversation(
    @BsonId
    val id: String = ObjectId().toString(),
    val participants: List<String>,
    val timestamp: Long
)
