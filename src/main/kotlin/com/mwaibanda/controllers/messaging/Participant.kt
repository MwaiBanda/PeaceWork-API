package com.mwaibanda.controllers.messaging

import io.ktor.http.cio.websocket.*

data class Participant(
    val username: String,
    val sessionId: String,
    val socket: WebSocketSession,
    val conversationId: String
    )
