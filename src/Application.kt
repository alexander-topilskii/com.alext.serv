package com.alext.serv

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.ContentType
import io.ktor.http.cio.websocket.*
import io.ktor.http.content.defaultResource
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.ServerSocket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.request.receiveParameters
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.cio.write
import io.ktor.utils.io.readUTF8Line
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.mapNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.InetSocketAddress
import java.time.Duration
import java.util.concurrent.Executors

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val client = HttpClient(Apache) {
        install(WebSockets)

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

            webSocket("/chat") {
                send("You are connected!")
                for(frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    send("You said: $receivedText")
                }
            }

        }
    }

}



