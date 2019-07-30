package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class CorePrefs(
    @SerializedName("auth_token") val authToken: String? = null,
    @SerializedName("refresh_token") val refreshToken: String? = null)