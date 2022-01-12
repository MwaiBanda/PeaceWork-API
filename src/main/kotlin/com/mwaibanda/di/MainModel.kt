package com.mwaibanda.di

import com.mwaibanda.data.dataSource.jobs.JobDataSource
import com.mwaibanda.data.dataSource.jobs.JobDataSourceImpl
import com.mwaibanda.main.conversationController.ConversationController
import com.mwaibanda.data.dataSource.messages.MessageDataSource
import com.mwaibanda.data.dataSource.messages.MessageDataSourceImpl
import com.mwaibanda.data.dataSource.users.UserDataSource
import com.mwaibanda.data.dataSource.users.UserDataSourceImpl
import com.mwaibanda.main.jobController.JobController
import com.mwaibanda.main.userController.UserController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
       KMongo.createClient()
           .coroutine
           .getDatabase("peacework_db")
    }

    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single {
        ConversationController(get())
    }

    single<UserDataSource> {
        UserDataSourceImpl(get())
    }

    single {
        UserController(get())
    }
    single<JobDataSource> {
        JobDataSourceImpl(get())
    }
    single {
        JobController(get())
    }
}