package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class SmsCodeRequest constructor(
    @SerializedName("mobile_phone")
    val phone: String)