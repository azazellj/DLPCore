package ua.com.wl.dlp.data.api.responses.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class TokenResponse(
    @SerializedName("token")
    val token: String)