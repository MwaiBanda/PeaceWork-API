package com.mwaibanda.data.dataSource.users

import com.mwaibanda.data.model.user.User

interface UserDataSource {
    suspend fun insertUser(user: User)
}