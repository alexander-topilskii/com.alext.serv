package com.alext.serv

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.ContentType
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.InetSocketAddress
import java.util.concurrent.Executors

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
fun main(args: Array<String>): Unit =
    runBlocking {
        val server = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().bind(InetSocketAddress("127.0.0.1", 2323))
        println("Started echo telnet server at ${server.localAddress}")

        while (true) {
            val socket = server.accept()

            launch {
                println("Socket accepted: ${socket.remoteAddress}")

                val input = socket.openReadChannel()
                val output = socket.openWriteChannel(autoFlush = true)

                try {
                    while (true) {
                        val line = input.readUTF8Line()

                        println("${socket.remoteAddress}: $line")
                        output.write("$line\r\n")
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    socket.close()
                }
            }
        }
    }

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



