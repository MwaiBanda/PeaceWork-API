package com.mwaibanda.main.messaging

import com.mwaibanda.data.source.messages.MessageDataSource
import com.mwaibanda.data.model.Message
import io.ktor.http.cio.websocket.*
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class MessageController(
    private val messageDataSource: MessageDataSource
){
    private val participants = ConcurrentHashMap<String, Participant>()
    fun onJoin(
        userId: String,
        sessionId: String,
        socket: WebSocketSession,
        conversationID: String
    ){
        if (participants.containsKey(userId)) {
            throw ParticipantAlreadyExistsException()
        }
        participants[userId] = Participant(
            username = userId,
            sessionId = sessionId,
            socket = socket,
            conversationId = conversationID
        )
    }

    suspend fun sendMessage(userId: String, message: String, conversationId: String){

        participants
            .values
            .filter { it.conversationId == conversationId }
            .forEach { participant ->
            val messageEntity = Message(
                text = message,
                userId = userId,
                timestamp = System.currentTimeMillis(),
                date = Clock.System.now().toString(),
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

    suspend fun tryDisconnect(userId: String){
        participants[userId]?.socket?.close()
        if (participants.containsKey(userId)) {
            participants.remove(userId)
        }
    }
}