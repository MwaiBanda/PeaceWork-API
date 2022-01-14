package com.mwaibanda.data.dataSource.users

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
         users.findOne(User::userID eq id).let {
            return it!!
        }
    }

    override suspend fun updateUser(id: String, user: User) {
        users.updateOne(User::userID eq id, set(
            User::createdOn setTo user.createdOn,
            User::fullname setTo user.fullname,
            User::email setTo user.email,
            User::userID setTo user.userID,
            User::company setTo user.company,
            User::position setTo user.position,
            User::dateStarted setTo user.dateStarted
        ))
    }

    override suspend fun deleteUser(id: String) {
        users.deleteOne(User::userID eq id)
    }
}