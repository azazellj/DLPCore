package ua.com.wl.dlp.data.api.responses.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignResponse(
    @Json(name = "token")
    val token: String,

    @Json(name = "refresh_token")
    val refreshToken: String
)