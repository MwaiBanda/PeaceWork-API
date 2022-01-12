package com.mwaibanda.main.jobController

import com.mwaibanda.data.dataSource.jobs.JobDataSource
import com.mwaibanda.data.model.Job

class JobController(
    private var jobDataSource: JobDataSource
) {
    suspend fun postJob(job: Job) {
        jobDataSource.insertJob(job)
    }
    suspend fun getAllJobs(): List<Job> {
        return jobDataSource.getAllJobs()
    }
    suspend fun getJobsById(id: String): List<Job> {
        return jobDataSource.getJobsById(id)
    }
}