package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val imageUrl: String,
    val value: String
)