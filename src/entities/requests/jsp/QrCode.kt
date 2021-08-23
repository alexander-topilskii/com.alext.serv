package entities.requests.jsp

import kotlinx.serialization.Serializable

@Serializable
data class QrCode(
    val accountData: AccountData,
    val expire: Expire,
    val infoButton: InfoButton,
    val mainHeader: MainHeader,
    val notification: Notification,
    val qr: Qr,
    val qrButtonText: String,
    val qrHeader: QrHeader,
    val qrImageUrl: String
)