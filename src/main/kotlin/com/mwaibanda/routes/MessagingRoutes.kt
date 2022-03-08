package com.mwaibanda.routes

import com.mwaibanda.controllers.messaging.MessageController
import com.mwaibanda.controllers.messaging.ParticipantAlreadyExistsException
import com.mwaibanda.sessions.ConversationSession
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach


fun Route.messageSocketRoute(messageController: MessageController){
    webSocket("/message/{id}") {
        val session = call.sessions.get<ConversationSession>()
        if(session == null) {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session"))
            return@webSocket
        }
        val params = call.receiveParameters()

        val id = params["id"].toString()

        try {
            messageController.onJoin(
                userId = session.username,
                sessionId = session.sessionId,
                socket = this,
                conversationID = id
            )
            incoming.consumeEach { frame ->
                if (frame is Frame.Text) {
                    messageController.sendMessage(
                        userId = session.username,
                        message = frame.readText(),
                        conversationId = id
                    )
                }
            }
        } catch (e: ParticipantAlreadyExistsException){
            call.respond(HttpStatusCode.Conflict)
        } catch (e : Exception) {
            e.printStackTrace()
        } finally {
            messageController.tryDisconnect(session.username)
        }
    }
}

fun Route.getAllMessagesForConversation(messageController: MessageController) {
    get("/messages/{id}") {
        val id = call.parameters["id"].toString()
        call.respond(
            HttpStatusCode.OK,
            messageController.getAllConversationMessages(conversationId = id)
        )
    }
}