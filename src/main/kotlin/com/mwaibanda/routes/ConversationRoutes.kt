package com.mwaibanda.routes

import com.mwaibanda.data.model.conversation.Conversation
import com.mwaibanda.data.model.conversation.LastSent
import com.mwaibanda.controllers.ConversationController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.MongoOperator


fun Route.userConversations(conversationController: ConversationController) {
    route("/conversations") {
        post {
            try {
                val conversation = call.receive<Conversation>()
                conversationController.postConversation(conversation)
                call.respond(HttpStatusCode.Created,  "Successfully Created conversation ${conversation.id}")
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        delete("/{conversationId}") {
            try {
                val id = call.parameters["conversationId"].toString()
                conversationController.deleteConversation(id)
                call.respond(HttpStatusCode.OK,  "Successfully deleted conversation $id")
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        get {
            try {
                val id = call.parameters["userId"].toString()
                val conversations: List<Conversation> = conversationController.getConversationsByUserID(id)
                call.respond(HttpStatusCode.OK,  conversations)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        put ("/last-sent/{conversationId}"){
            val params = call.receiveParameters()
            val conversationId = params["conversationId"].toString()
            val lastSentDate = params["lastSentDate"].toString()
            val userId = params["userId"].toString()
            val message = params["message"].toString()
            val isSeen = params["isSeen"].toBoolean()
            try {
                conversationController.updateLastSent(
                    conversationId,
                    LastSent(
                        userId = userId,
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