package com.mwaibanda.routes

import com.mwaibanda.data.model.User
import com.mwaibanda.controllers.UserController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getUserById(userController: UserController){
    get("/users/{id}") {
        try {
            val id = call.parameters["id"].toString()
            val user: User = userController.getUserById(id)
            call.respond(HttpStatusCode.OK, user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun Route.deleteUser(userController: UserController){
    delete("/users/{id}") {
        try {
            val id = call.parameters["id"].toString()
            userController.deleteUser(id)
            call.respond(HttpStatusCode.OK, "DELETED USER {ID} - $id")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
fun Route.postUser(userController: UserController) {
    post("/users") {
        val paramas = call.receiveParameters()
        val createdOn = paramas["createdOn"].toString()
        val fullname = paramas["fullname"].toString()
        val email = paramas["email"].toString()
        val userID = paramas["userID"].toString()
        val company = paramas["company"].toString()
        val position = paramas["position"].toString()
        val dateStarted = paramas["dateStarted"].toString()

        userController.postUser(
            User(
            createdOn = createdOn,
            fullname = fullname,
            email = email,
            userID = userID,
            company = company,
            position = position,
            dateStarted = dateStarted
        )
        )
        call.respond(HttpStatusCode.OK,"SUCCESS")
    }
}

fun Route.updateUser(userController: UserController) {
    put("/users/{id}") {
        val paramas = call.receiveParameters()
        val createdOn = paramas["createdOn"].toString()
        val fullname = paramas["fullname"].toString()
        val email = paramas["email"].toString()
        val userID = paramas["id"].toString()
        val company = paramas["company"].toString()
        val position = paramas["position"].toString()
        val dateStarted = paramas["dateStarted"].toString()

        userController.updateUser(
            userID, User(
            createdOn = createdOn,
            fullname = fullname,
            email = email,
            userID = userID,
            company = company,
            position = position,
            dateStarted = dateStarted
        )
        )
        call.respond(HttpStatusCode.OK,"SUCCESS")
    }
}

