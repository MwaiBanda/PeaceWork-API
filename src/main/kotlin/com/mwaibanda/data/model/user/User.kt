package com.mwaibanda.data.model.user

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.LongAsStringSerializer
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class User (
    @BsonId
    val id: String = ObjectId().toString(),
    var createdOn: String,
    var fullname: String,
    var email: String,
    var userID: String,
    var company: String,
    var position: String,
    var dateStarted: String,
    var contacts: List<Contact>
)

