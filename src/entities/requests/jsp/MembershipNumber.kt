package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class MembershipNumber(
    val title: String,
    val value: Long
)