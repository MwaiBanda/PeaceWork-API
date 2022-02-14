package com.mwaibanda.data.model.messaging

import kotlinx.serialization.Serializable

@Serializable
data class LastSent(
    val id: String,
    val username: String,
    val message: String,
    val isSeen: Boolean
)
