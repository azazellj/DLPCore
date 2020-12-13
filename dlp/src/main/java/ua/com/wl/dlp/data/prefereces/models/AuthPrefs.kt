package ua.com.wl.dlp.data.prefereces.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthPrefs(
    @Json(name = "auth_token")
    val authToken: String? = null,

    @Json(name = "refresh_token")
    val refreshToken: String? = null
)