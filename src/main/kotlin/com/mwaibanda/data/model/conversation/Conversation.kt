package com.mwaibanda.data.model.conversation

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Conversation(
    @BsonId
    val id: String,
    val participants: List<Participant>,
    val lastSent: LastSent,
    val timestamp: Long
)
