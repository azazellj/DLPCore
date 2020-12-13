package ua.com.wl.dlp.data.api.requests.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenRequest(
    @Json(name = "token")
    val token: String?
)