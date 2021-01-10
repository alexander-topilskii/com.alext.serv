package com.alext.serv

import com.alext.serv.entities.requests.RoomResponse
import com.alext.serv.entities.requests.RoomSettingsRequest
import com.alext.serv.entities.requests.SuccessResponse
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.websocket.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.util.Identity.decode
import io.ktor.util.Identity.encode
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToString
import java.time.Duration

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val controller = Controller()
    val client = HttpClient(Apache) {
        install(JsonFeature)
        install(ContentNegotiation) { json() }

        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(60)
            timeout = Duration.ofSeconds(15)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }

        routing {
            get("/kek") {
                call.respondText("Hello Local World!", ContentType.Text.Plain)
            }

            get("/getRoom") {
                val roomName = call.parameters["roomName"]

                try {
                    val settings = controller.getRoom(roomName)

                    call.respond(settings)
                } catch (e: Throwable) {
                    call.respond(HttpStatusCode(500, e.message!!))
                }
            }

            post("/setRoomSettings") {
                val roomSettingsRequest = call.receive<RoomSettingsRequest>()

                try {
                    controller.setRoomSettings(roomSettingsRequest)

                    call.respond(SuccessResponse)
                } catch (e: Throwable) {
                    call.respond(HttpStatusCode(500, e.message!!))
                }

                call.respondText("Hello Local World!", ContentType.Text.Plain)
            }

            post("/createRoom") {
                val roomResponse = call.receive<RoomResponse>()

                try {
                    controller.createRoom(roomResponse)
                    call.respond(SuccessResponse)
                } catch (e: Throwable) {
                    call.respond(HttpStatusCode(500, e.message!!))
                }

            }

            post("/enterRoom") {
                val roomResponse = call.receive<RoomResponse>()

                try {
                    controller.enterRoom(roomResponse)
                    call.respond(SuccessResponse)
                } catch (e: Throwable) {
                    call.respond(HttpStatusCode(500, e.message!!))
                }
            }

            webSocket("/chat") {
                send("You are connected!")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    send("You said: $receivedText")
                }
            }

            webSocket("/observeRoom/{room_id}") {
                val room_id = call.parameters["room_id"]
                println("GGGG: observeRoom $room_id")

                for (frame in incoming) {
                    val room = controller.getRoom(room_id)
                    val json = Json.encodeToString(room)

                    send(json)


                }


            }

        }
    }

}



