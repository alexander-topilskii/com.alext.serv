package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class Expire(
    val expiryText: String,
    val isDate: Boolean
)