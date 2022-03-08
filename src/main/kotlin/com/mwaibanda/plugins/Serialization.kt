package com.mwaibanda.plugins

import io.ktor.serialization.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Cbor
import io.ktor.http.ContentType.Application.Json
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.content

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })


    }

}
