package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class AdditionalData(
    val buttonText: String,
    val header: String,
    val items: List<Item>
)