package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class AuthPrefs constructor(
    @SerializedName("auth_token")
    val authToken: String? = null,

    @SerializedName("refresh_token")
    val refreshToken: String? = null)