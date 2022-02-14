package com.mwaibanda.data.source.jobs

import com.mwaibanda.data.model.Job

interface JobDataSource {
    suspend fun insertJob(job: Job)
    suspend fun getAllJobs(): List<Job>
    suspend fun getJobsByUserId(userID: String): List<Job>
    suspend fun updateJob(id: String, job: Job)
    suspend fun deleteJob(id: String)

}