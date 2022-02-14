package com.mwaibanda.routes

import com.mwaibanda.data.model.Job
import com.mwaibanda.data.model.messaging.Conversation
import com.mwaibanda.main.conversations.ConversationController
import com.mwaibanda.main.messaging.MessageController
import io.ktor.application.*
import io.ktor.http.*
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