package com.mwaibanda.routes

import com.mwaibanda.main.conversationController.ConversationController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

//
//fun Route.conversationSocketRoutes(conversationController: ConversationController){
//    webSocket("/converse") {
//        val session = call.sessions.get<ConversationSession>()
//        if(session == null) {
//            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session"))
//            return@webSocket
//        }
//        try {
//            conversationController.onJoin(
//                username = session.username,
//                sessionId = session.sessionId,
//                socket = this
//            )
//            incoming.consumeEach { frame ->
//                if (frame is Frame.Text) {
//                    conversationController.sendMessage(
//                        username = session.username,
//                        message = frame.readText(),
//                        sender = "1",
//                        recipient = "2",
//                        conversationId = "001"
//                    )
//                }
//            }
//        } catch (e: ParticipantAlreadyExistsException){
//            call.respond(HttpStatusCode.Conflict)
//        } catch (e : Exception) {
//            e.printStackTrace()
//        }
//    }
//}

fun Route.getAllMessages(conversationController: ConversationController) {
    get("/messages") {
        call.respond(
            HttpStatusCode.OK,
            conversationController.getAllMessage(conversationId = "001")
        )
    }
}