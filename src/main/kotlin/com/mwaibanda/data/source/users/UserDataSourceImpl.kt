package com.mwaibanda.data.source.users

import com.mwaibanda.data.model.user.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

class UserDataSourceImpl(
    private val db: CoroutineDatabase
): UserDataSource {
    private val users = db.getCollection<User>()

    override suspend fun insertUser(user: User) {
       users.insertOne(user)
    }

    override suspend fun getUserById(id: String): User {
        return users.findOne(User::userID eq id) ?:
        User(
            id = "PW-U00000-P6W-384",
            createdOn = "12/12/2021",
            fullname = "Guest",
            email = "guest@peacework.com",
            userID = "U00000-P6W",
            company = "-",
            position = "-",
            dateStarted = "-",
            emptyList()
        )
    }

    override suspend fun updateUser(id: String, user: User) {
        users.updateOne(
            User::userID eq id, set(
            User::createdOn setTo user.createdOn,
            User::fullname setTo user.fullname,
            User::email setTo user.email,
            User::company setTo user.company,
            User::position setTo user.position,
            User::dateStarted setTo user.dateStarted
        ))
    }

    override suspend fun deleteUser(id: String) {
        users.deleteOne(User::userID eq id)
    }
}