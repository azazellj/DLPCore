package ua.com.wl.dlp.data.api.requests.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationRequest(
    @Json(name = "send_sms")
    val sendSms: Boolean,

    @Json(name = "mobile_phone")
    val mobilePhone: String
)