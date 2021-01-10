package com.alext.serv.entities.requests

import com.alext.serv.entities.Settings
import kotlinx.serialization.Serializable

@Serializable
data class RoomSettingsRequest(val roomName: String, val settings: Settings)