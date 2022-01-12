package com.mwaibanda.data.dataSource.users

import com.mwaibanda.data.model.user.User
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserDataSourceImpl(
    private val db: CoroutineDatabase
): UserDataSource {
    private val users = db.getCollection<User>()

    override suspend fun insertUser(user: User) {
       users.insertOne(user)
    }
}