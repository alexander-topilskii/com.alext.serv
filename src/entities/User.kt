package com.alext.serv.entities
import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val isOwner: Boolean)

@Serializable
data class Customer(val id: Int, val firstName: String, val lastName: String)