package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val additionalData: AdditionalData,
    val description: String
)