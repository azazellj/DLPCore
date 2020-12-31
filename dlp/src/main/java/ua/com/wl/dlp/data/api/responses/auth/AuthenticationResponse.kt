package ua.com.wl.dlp.data.api.responses.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import ua.com.wl.dlp.data.api.responses.models.auth.AuthWay

@JsonClass(generateAdapter = true)
data class AuthenticationResponse(
    @Json(name = "is_registered")
    val isRegistered: Boolean,

    @Json(name = "registration_way")
    val authWay: AuthWay
)