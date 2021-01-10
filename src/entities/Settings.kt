package com.alext.serv.entities

import kotlinx.serialization.Serializable

@Serializable
data class Settings(val map: MutableMap<String, String> = mutableMapOf())