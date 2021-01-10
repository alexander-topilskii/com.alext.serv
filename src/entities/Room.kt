package com.alext.serv.entities

import kotlinx.serialization.Serializable


@Serializable
data class Room(val name: String, val users: MutableList<User>, val settings: Settings)

