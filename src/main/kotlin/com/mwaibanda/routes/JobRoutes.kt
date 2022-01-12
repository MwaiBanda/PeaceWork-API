package com.mwaibanda.routes

import com.mwaibanda.data.model.Job
import com.mwaibanda.main.jobController.JobController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

 fun Route.getAllJobs(jobController: JobController) {
     get("/jobs") {
         try {
             val jobs: List<Job> = jobController.getAllJobs()
             call.respond(HttpStatusCode.OK,  jobs)
         } catch (e: Exception){
             e.printStackTrace()
         }
     }

 }
fun Route.getJobsById(jobController: JobController){
    get("/jobs/{id}"){
        try {
            val id = call.parameters["id"].toString()
            val jobs: List<Job> = jobController.getJobsById(id)
            call.respond(HttpStatusCode.OK, jobs)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}
fun Route.postJob(jobController: JobController){
    post("/post-job") {
        val paramas = call.receiveParameters()
        val title = paramas["title"].toString()
        val company = paramas["company"].toString()
        val location = paramas["location"].toString()
        val pay = paramas["pay"].toString()
        val payRate = paramas["payRate"].toString()
        val type = paramas["type"].toString()
        val employmentType = paramas["type"].toString()
        val description = paramas["description"].toString()
        val qualifications = paramas["qualifications"].toString()
        val responsibilities = paramas["responsibilities"].toString()
        val publisher = paramas["publisher"].toString()
        val startDate = paramas["startDate"].toString()
        val contactEmail = paramas["contactEmail"].toString()

        jobController.postJob(
            Job(
                title = title,
                company = company,
                location = location,
                pay = pay,
                payRate = payRate,
                type = type,
                employmentType = employmentType,
                description =  description,
                qualifications = qualifications,
                responsibilities = responsibilities,
                publisher = publisher,
                startDate = startDate,
                contactEmail = contactEmail,
            )
        )
        call.respond(HttpStatusCode.OK,"SUCCESS")
    }
}