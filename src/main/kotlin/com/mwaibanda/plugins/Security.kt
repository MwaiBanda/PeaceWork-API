package com.mwaibanda.plugins

import com.mwaibanda.sessions.ConversationSession
import io.ktor.sessions.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.util.*

fun Application.configureSecurity() {
//    install(Sessions) {
//        cookie<ConversationSession>("SESSION")
//    }
//
//    intercept(ApplicationCallPipeline.Features){
//        if (call.sessions.get<ConversationSession>() == null){
//            val username = call.parameters["username"] ?: "Guest"
//            call.sessions.set(ConversationSession(username, generateNonce()))
//        }
//    }
}
