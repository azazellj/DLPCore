package ua.com.wl.dlp.data.api.responses.consumer.referral

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QrCodeResponse(
    @Json(name = "qr_code")
    val qrCode: String
)