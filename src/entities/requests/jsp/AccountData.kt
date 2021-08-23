package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class AccountData(
    val accountNumber: String,
    val description: String
)