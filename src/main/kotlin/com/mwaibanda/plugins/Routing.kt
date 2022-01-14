package com.mwaibanda.plugins

import com.mwaibanda.main.conversationController.ConversationController
import com.mwaibanda.main.jobController.JobController
import com.mwaibanda.main.userController.UserController
import com.mwaibanda.routes.*
//import com.mwaibanda.routes.conversationSocketRoutes
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val conversationController: ConversationController by inject()
    val userController: UserController by inject()
    val jobController: JobController by inject()

    install(Routing){
        getAllMessages(conversationController)
        userRoutes(userController)
        postJob(jobController)
        getAllJobs(jobController)
        getUserJobsById(jobController)
        updateJob(jobController)
        deleteJob(jobController)
    }
}

