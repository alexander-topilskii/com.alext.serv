package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class CardholderName(
    val title: String,
    val value: String
)