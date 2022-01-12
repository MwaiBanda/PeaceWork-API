package com.mwaibanda.main.conversationController

import io.ktor.http.cio.websocket.*

data class Participant(
    val username: String,
    val sessionId: String,
    val socket: WebSocketSession
)
