package com.mwaibanda.data.model.messaging

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val id: String,
    val username: String
)
