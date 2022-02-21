package com.mwaibanda.routes

import com.mwaibanda.data.model.conversation.Conversation
import com.mwaibanda.data.model.conversation.LastSent
import com.mwaibanda.main.ConversationController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.userConversations(conversationController: ConversationController) {
    route("conversations") {
        get ("{userId}"){
            try {
                val id = call.parameters["userId"].toString()
                val conversations: List<Conversation> = conversationController.getConversationsByUserID(id)
                call.respond(HttpStatusCode.OK,  conversations)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        put ("last-sent/{conversationId}"){
            val params = call.receiveParameters()
            val conversationId = params["conversationId"].toString()
            val lastSentDate = params["lastSentDate"].toString()
            val username = params["username"].toString()
            val message = params["message"].toString()
            val isSeen = params["isSeen"].toBoolean()
            try {
                conversationController.updateLastSent(
                    conversationId,
                    LastSent(
                        userId = username,
                        message = message,
                        lastSentDate = lastSentDate,
                        isSeen = isSeen
                    )
                )
                call.respond(HttpStatusCode.OK)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}


//conversationController.postConversation(Conversation(
//id = "C22-A576544E",
//participants = listOf(
//Member(id = "TlwcMClcKvev3JwvqlJpeQObAgz1", username = "Mwai Banda"),
//Member(id = "hDu81Ly1rMeltjW7j4r4qFN25uw2", username = "Amanda Chimbuka")
//),
//lastSent = LastSent(
//id ="TlwcMClcKvev3JwvqlJpeQObAgz1",
//username = "Mwai Banda",
//message = "Hi, we need to talk!",
//isSeen = false
//),
//timestamp = System.currentTimeMillis(),
//))