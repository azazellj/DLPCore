package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("city")
    val city: Int,
    @SerializedName("mobile_phone")
    val phone: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("barcode")
    val barcode: String? = null)