package com.mwaibanda.plugins

import com.mwaibanda.main.conversations.ConversationController
import com.mwaibanda.main.messaging.MessageController
import com.mwaibanda.main.jobs.JobController
import com.mwaibanda.main.users.UserController
import com.mwaibanda.routes.*
//import com.mwaibanda.routes.conversationSocketRoutes
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val messageController: MessageController by inject()
    val userController: UserController by inject()
    val jobController: JobController by inject()
    val  conversationController: ConversationController by inject()

    install(Routing){
        /* MESSAGING ROUTES */
        getAllConversationMessages(messageController)
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
    }
}

