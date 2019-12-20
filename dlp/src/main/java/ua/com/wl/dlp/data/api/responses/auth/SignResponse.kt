package ua.com.wl.dlp.data.api.responses.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SignResponse constructor(
    @SerializedName("token") 
    val token: String,
    
    @SerializedName("refresh_token") 
    val refreshToken: String)