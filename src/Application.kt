package com.alext.serv

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.ContentType
import io.ktor.request.receiveParameters
import io.ktor.routing.get
import io.ktor.routing.post
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
                call.respondText("Hello Local World!", ContentType.Text.Plain)
            }

            post("/test2") {
                val parameters = call.receiveParameters()

                val paramVal1 = parameters["param1"]
                val paramVal2 = parameters["param2"]

                call.respondText("This is a test POST request with parameter values $paramVal1 and $paramVal2 and params: $parameters")
            }
        }
    }


}



