package ua.com.wl.dlp.data.api.responses.consumer.feedback

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedbackResponse(
    @Json(name = "email")
    val email: String,

    @Json(name = "mobile_phone")
    val phone: String,

    @Json(name = "message")
    val message: String,

    @Json(name = "app_version")
    val appVersion: String,

    @Json(name = "mobile_phone_info")
    val deviceInfo: String
)