package com.alext.serv.entities.requests

import kotlinx.serialization.Serializable

@Serializable
data class RoomRequest(val roomName: String, val userName: String)