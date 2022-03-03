package com.mwaibanda.controllers

import com.mwaibanda.data.model.User
import com.mwaibanda.data.source.users.UserDataSource

class UserController(
    private val userDataSource: UserDataSource
) {
    suspend fun postUser(user: User) {
        userDataSource.insertUser(user)
    }
    suspend fun getUserById(id: String): User {
        return userDataSource.getUserById(id)
    }
    suspend fun updateUser(id: String, user: User) {
        return userDataSource.updateUser(id, user)
    }
    suspend fun deleteUser(id: String){
        userDataSource.deleteUser(id)
    }
}