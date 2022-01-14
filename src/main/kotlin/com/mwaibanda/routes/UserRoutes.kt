package com.mwaibanda.routes

import com.mwaibanda.data.model.user.User
import com.mwaibanda.main.userController.UserController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bson.types.ObjectId
import java.util.*
import kotlinx.serialization.Serializable

@Serializable
data class  UserWrapper(
    val data: List<User>
)

fun Route.userRoutes(userController: UserController) {

    post("/users") {
        val paramas = call.receiveParameters()
        val createdOn = paramas["createdOn"].toString()
        val fullname = paramas["fullname"].toString()
        val email = paramas["email"].toString()
        val userID = paramas["userID"].toString()
        val company = paramas["company"].toString()
        val position = paramas["position"].toString()
        val dateStarted = paramas["dateStarted"].toString()

        userController.postUser(User(
            id = ObjectId.get().toString(),
            createdOn = createdOn,
            fullname = fullname,
            email = email,
            userID = userID,
            company = company,
            position = position,
            dateStarted = dateStarted
        ))
        call.respond(HttpStatusCode.OK,"SUCCESS")
    }

    get("/users") {
        call.respond(HttpStatusCode.OK, User(
            id = ObjectId.get().toString(),
            createdOn = "Tue Dec 28",
            fullname = "Musenya Ngoma",
            email = "admin@admin.com",
            userID = UUID.randomUUID().toString(),
            company = "PeaceWork Inc",
            position = "Software Dev",
            dateStarted = "Tue Dec 28"
        )
        )
    }
}

