package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class InfoButton(
    val buttonText: String,
    val buttonUrl: String
)