package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class MainHeader(
    val subtitle: String,
    val title: String
)