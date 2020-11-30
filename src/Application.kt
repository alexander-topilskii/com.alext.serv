package com.alext.serv

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.ContentType
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val client = HttpClient(Apache) {
        routing {
            get("/kek") {
                call.respondText("Hello World!2", ContentType.Text.Plain)
            }
        }
    }


}



