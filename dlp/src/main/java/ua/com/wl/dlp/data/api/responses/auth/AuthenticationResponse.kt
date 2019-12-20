package ua.com.wl.dlp.data.api.responses.auth

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.auth.AuthWay

/**
 * @author Denis Makovskyi
 */

data class AuthenticationResponse constructor(
    @SerializedName("is_registered")
    val isRegistered: Boolean,

    @SerializedName("registration_way")
    val authWay: AuthWay)