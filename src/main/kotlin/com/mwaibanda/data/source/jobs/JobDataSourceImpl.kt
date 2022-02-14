package com.mwaibanda.data.source.jobs

import com.mwaibanda.data.model.Job
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

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

    override suspend fun getJobsByUserId(userID: String): List<Job> {
        return jobsCollection.find(Job::publisher eq userID).toList()
    }

    override suspend fun updateJob(id: String, job: Job) {
        jobsCollection.updateOne(Job::id eq id, set(
            Job::title setTo job.title,
            Job::company setTo job.company,
            Job::location setTo job.location,
            Job::pay setTo job.pay,
            Job::payRate setTo job.payRate,
            Job::type setTo job.type,
            Job::employmentType setTo job.employmentType,
            Job::description setTo job.description,
            Job::qualifications setTo job.qualifications,
            Job::responsibilities setTo job.responsibilities,
            Job::startDate setTo job.startDate,
            Job::publisher setTo job.publisher,
            Job::contactEmail setTo job.contactEmail,
        ))
    }

    override suspend fun deleteJob(id: String) {
        jobsCollection.deleteOne( Job::id eq id)
    }
}