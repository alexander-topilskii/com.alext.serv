package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class Qr(
    val cardholderName: CardholderName,
    val membershipNumber: MembershipNumber,
    val qrBase64: String
)