package com.mwaibanda.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Job(
    @BsonId
    val id: String = ObjectId().toString(),
    val title: String,
    val company: String,
    val location: String,
    val pay: String,
    val payRate: String,
    val type: String,
    val employmentType: String,
    val description: String,
    val qualifications: String,
    val responsibilities: String,
    val startDate: String,
    val publisher: String,
    val contactEmail: String,
)



// DATACLASS::JOB FUTURE ADDITIONS
/*   val openPositions: Int,
     val schedule: List<Schedule>,
     val benefits: List<Benefits>,
 */







