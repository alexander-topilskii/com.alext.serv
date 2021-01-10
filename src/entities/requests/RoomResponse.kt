package com.alext.serv.entities.requests

import kotlinx.serialization.Serializable

@Serializable
data class RoomResponse(val roomName: String, val userName: String)