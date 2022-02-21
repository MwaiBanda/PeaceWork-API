package com.mwaibanda.routes

import com.mwaibanda.data.model.Job
import com.mwaibanda.main.ConversationController
import com.mwaibanda.main.JobController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getAllJobs(jobController: JobController, conversationController: ConversationController) {
     get("/jobs") {
         try {
             val jobs: List<Job> = jobController.getAllJobs()


             call.respond(HttpStatusCode.OK,  jobs)
         } catch (e: Exception){
             e.printStackTrace()
         }
     }

 }
fun Route.getUserJobsById(jobController: JobController){
    get("/jobs/user/{id}"){
        try {
            val id = call.parameters["id"].toString()
            val jobs: List<Job> = jobController.getUserJobsById(id)
            call.respond(HttpStatusCode.OK, jobs)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}

fun Route.updateJob(jobController: JobController){
    put("/jobs/{id}") {
        val params = call.receiveParameters()
        val id = params["id"].toString()
        val title = params["title"].toString()
        val company = params["company"].toString()
        val location = params["location"].toString()
        val pay = params["pay"].toString()
        val payRate = params["payRate"].toString()
        val type = params["type"].toString()
        val employmentType = params["type"].toString()
        val description = params["description"].toString()
        val qualifications = params["qualifications"].toString()
        val responsibilities = params["responsibilities"].toString()
        val publisher = params["publisher"].toString()
        val startDate = params["startDate"].toString()
        val contactEmail = params["contactEmail"].toString()
        try {
            jobController.updateJob(id, Job(
                title = title,
                company = company,
                location = location,
                pay = pay,
                payRate = payRate,
                type = type,
                employmentType = employmentType,
                description = description,
                qualifications = qualifications,
                responsibilities = responsibilities,
                publisher = publisher,
                startDate = startDate,
                contactEmail = contactEmail
            ))
            call.respond(HttpStatusCode.OK,"UPDATED JOB {ID} - $id")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
fun Route.deleteJob(jobController: JobController){
    delete("/jobs/{id}"){
        try {
            val id = call.parameters["id"].toString()
            jobController.deleteJob(id)
            call.respond(HttpStatusCode.OK, "DELETED JOB {ID} - $id")
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

}
fun Route.postJob(jobController: JobController){
    post("/jobs") {
        val params = call.receiveParameters()
        val title = params["title"].toString()
        val company = params["company"].toString()
        val location = params["location"].toString()
        val pay = params["pay"].toString()
        val payRate = params["payRate"].toString()
        val type = params["type"].toString()
        val employmentType = params["type"].toString()
        val description = params["description"].toString()
        val qualifications = params["qualifications"].toString()
        val responsibilities = params["responsibilities"].toString()
        val publisher = params["publisher"].toString()
        val startDate = params["startDate"].toString()
        val contactEmail = params["contactEmail"].toString()

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
        call.respond(HttpStatusCode.OK,"POSTED JOB {TITLED} - $title")
    }
}