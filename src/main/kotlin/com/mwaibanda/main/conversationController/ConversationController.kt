package com.mwaibanda.main.conversationController

import com.mwaibanda.data.dataSource.messages.MessageDataSource
import com.mwaibanda.data.model.messages.Message
import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class ConversationController(
    private val messageDataSource: MessageDataSource
){
    private val participants = ConcurrentHashMap<String, Participant>()
    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ){
        if (participants.containsKey(username)) {
            throw ParticipantAlreadyExistsException()
        }
        participants[username] = Participant(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    suspend fun sendMessage(username: String, message: String, sender: String, recipient: String, conversationId: String){

        participants.values.forEach { participant ->
            val messageEntity = Message(
                text = message,
                username = username,
                timestamp = System.currentTimeMillis(),
                sender = sender,
                recipient = recipient,
                conversationId = conversationId
            )
            messageDataSource.insertMessage(messageEntity)
            val parsedMessage = Json.encodeToString(messageEntity)
            participant.socket.send(Frame.Text(parsedMessage))
        }

    }

    suspend fun getAllMessage(conversationId: String): List<Message> {
        return messageDataSource.getAllMessagesForConversation(conversationId)
    }

    suspend fun tryDisconnect(username: String){
        participants[username]?.socket?.close()

        if (participants.containsKey(username)) {
            participants.remove(username)
        }
    }


}