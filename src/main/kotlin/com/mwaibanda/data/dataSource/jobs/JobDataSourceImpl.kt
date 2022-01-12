package com.mwaibanda.data.dataSource.jobs

import com.mwaibanda.data.model.Job
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class JobDataSourceImpl(
    private val dataBase: CoroutineDatabase
): JobDataSource {
    private val jobsCollection = dataBase.getCollection<Job>()
    override suspend fun insertJob(job: Job) {
        jobsCollection.insertOne(job)
    }

    override suspend fun getAllJobs(): List<Job> {
        return jobsCollection.find().toList()
    }

    override suspend fun getJobsById(id: String): List<Job> {
        return jobsCollection.find(Job::publisher eq id).toList()
    }
}