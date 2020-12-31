package ua.com.wl.dlp.data.api.requests.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardsStatusRequest(
    @Json(name = "mobile_phone")
    val phone: String,

    @Json(name = "password")
    val password: String
)