package com.mwaibanda.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val userID: String,
    val username: String
)
