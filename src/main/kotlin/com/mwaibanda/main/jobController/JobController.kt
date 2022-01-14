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
    suspend fun getUserJobsById(id: String): List<Job> {
        return jobDataSource.getJobsByUserId(id)
    }
    suspend fun updateJob(id: String, job: Job){
        jobDataSource.updateJob(id, job)
    }
    suspend fun deleteJob(id: String) {
        jobDataSource.deleteJob(id)
    }

}