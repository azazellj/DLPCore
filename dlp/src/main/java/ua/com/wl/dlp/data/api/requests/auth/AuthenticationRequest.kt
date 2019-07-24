package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

data class AuthenticationRequest(
    @SerializedName("send_sms")
    val sendSms: Boolean,
    @SerializedName("mobile_phone")
    val mobilePhone: String)