package com.mwaibanda.plugins

import com.mwaibanda.controllers.ConversationController
import com.mwaibanda.controllers.messaging.MessageController
import com.mwaibanda.controllers.JobController
import com.mwaibanda.controllers.UserController
import com.mwaibanda.routes.*
//import com.mwaibanda.routes.conversationSocketRoutes
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.content.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val messageController: MessageController by inject()
    val userController: UserController by inject()
    val jobController: JobController by inject()
    val  conversationController: ConversationController by inject()

    install(Routing){
        /* MESSAGING ROUTES */
        getAllMessagesForConversation(messageController)
        messageSocketRoute(messageController)
        /* CONVERSATION ROUTES */
        userConversations(conversationController)
        /* USER ROUTES */
        postUser(userController)
        getUserById(userController)
        updateUser(userController)
        deleteUser(userController)
        /* JOBS ROUTES */
        postJob(jobController)
        getAllJobs(jobController, conversationController)
        getUserJobsById(jobController)
        updateJob(jobController)
        deleteJob(jobController)
        static("/assets") {
            resources("assets")
        }
    }
}

