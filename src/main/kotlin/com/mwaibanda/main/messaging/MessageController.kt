package com.mwaibanda.main.messaging

import com.mwaibanda.data.source.messages.MessageDataSource
import com.mwaibanda.data.model.Message
import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class MessageController(
    private val messageDataSource: MessageDataSource
){
    private val participants = ConcurrentHashMap<String, Participant>()
    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession,
        conversationID: String
    ){
        if (participants.containsKey(username)) {
            throw ParticipantAlreadyExistsException()
        }
        participants[username] = Participant(
            username = username,
            sessionId = sessionId,
            socket = socket,
            conversationId = conversationID
        )
    }

    suspend fun sendMessage(username: String, message: String, conversationId: String){

        participants
            .values
            .filter { it.conversationId == conversationId }
            .forEach { participant ->
            val messageEntity = Message(
                text = message,
                username = username,
                timestamp = System.currentTimeMillis(),
                conversationId = conversationId
            )
            messageDataSource.insertMessage(messageEntity)
            val parsedMessage = Json.encodeToString(messageEntity)
            participant.socket.send(Frame.Text(parsedMessage))
        }

    }

    suspend fun getAllConversationMessages(conversationId: String): List<Message> {
        return messageDataSource.getAllMessagesForConversation(conversationId)
    }

    suspend fun tryDisconnect(conversationId: String, username: String){
        participants[username]?.socket?.close()
        if (participants.containsKey(username)) {
            participants.remove(username)
        }
    }
}