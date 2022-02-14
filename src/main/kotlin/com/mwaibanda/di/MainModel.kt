package com.mwaibanda.di

import com.mwaibanda.data.source.conversations.ConversationDataSource
import com.mwaibanda.data.source.conversations.ConversationDataSourceImpl
import com.mwaibanda.data.source.jobs.JobDataSource
import com.mwaibanda.data.source.jobs.JobDataSourceImpl
import com.mwaibanda.main.messaging.MessageController
import com.mwaibanda.data.source.messages.MessageDataSource
import com.mwaibanda.data.source.messages.MessageDataSourceImpl
import com.mwaibanda.data.source.users.UserDataSource
import com.mwaibanda.data.source.users.UserDataSourceImpl
import com.mwaibanda.main.ConversationController
import com.mwaibanda.main.JobController
import com.mwaibanda.main.UserController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
       KMongo.createClient()
           .coroutine
           .getDatabase("peacework_db")
    }
    single<ConversationDataSource> {
        ConversationDataSourceImpl(get())
    }
    single { ConversationController(get()) }

    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single { MessageController(get()) }

    single<UserDataSource> {
        UserDataSourceImpl(get())
    }

    single {
        UserController(get())
    }

    single<JobDataSource> {
        JobDataSourceImpl(get())
    }
    single { JobController(get()) }
}