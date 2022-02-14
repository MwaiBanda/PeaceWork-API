package com.mwaibanda.data.source.users

import com.mwaibanda.data.model.user.User

interface UserDataSource {
    suspend fun insertUser(user: User)
    suspend fun getUserById(id: String): User
    suspend fun updateUser(id: String, user: User)
    suspend fun deleteUser(id: String)
}