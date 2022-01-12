package com.mwaibanda.main.userController

import com.mwaibanda.data.model.user.User
import com.mwaibanda.data.dataSource.users.UserDataSource

class UserController(
    private val userDataSource: UserDataSource
) {
    suspend fun postUser(user: User) {
        userDataSource.insertUser(user)
    }
}