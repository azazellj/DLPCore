package ua.com.wl.dlp.data.api.responses.auth

import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(@SerializedName("authorization_token") val token: String)