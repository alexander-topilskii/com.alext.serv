package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class QrHeader(
    val subtitle: String,
    val title: String
)