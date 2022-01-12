package com.mwaibanda.data.dataSource.jobs

import com.mwaibanda.data.model.Job

interface JobDataSource {
    suspend fun insertJob(job: Job)
    suspend fun getAllJobs(): List<Job>
    suspend fun getJobsById(id: String): List<Job>

}