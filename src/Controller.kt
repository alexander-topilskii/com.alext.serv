package com.alext.serv

import com.alext.serv.entities.Room
import com.alext.serv.entities.Settings
import com.alext.serv.entities.User
import com.alext.serv.entities.requests.RoomRequest
import com.alext.serv.entities.requests.RoomSettingsRequest
import com.alext.serv.entities.requests.jsp.qrJson1
import com.alext.serv.exceptions.NoRoomException
import com.alext.serv.exceptions.UnknownException
import entities.requests.jsp.QrCode
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class Controller {


    fun getQr1(): QrCode = Json.decodeFromString<QrCode>(qrJson1)

    private val rooms = hashMapOf<String, Room>()

    fun createRoom(roomRequest: RoomRequest?) {
        roomRequest?.let {
            rooms[it.roomName] = Room(
                it.roomName,
                mutableListOf(User(it.userName, true)),
                Settings()
            )
        }
    }

    fun enterRoom(roomRequest: RoomRequest?) {
        roomRequest?.let {
            rooms[it.roomName]?.let { room ->
                room.apply {
                    users.add(User(it.userName, false))
                }
            } ?: throw NoRoomException()
        }
    }

    fun getRoom(roomName: String?): Room {
        roomName?.let {
           return rooms[roomName]?: throw NoRoomException()
        }
        throw UnknownException()
    }

    fun setRoomSettings(roomSettingsRequest: RoomSettingsRequest?) {
        roomSettingsRequest?.let {
            rooms[it.roomName]?.let { room ->
                room.apply {
                    settings.map.putAll(roomSettingsRequest.settings.map)
                }
            } ?: throw NoRoomException()
        }
        throw UnknownException()
    }

}


