package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

data class CodeRequest(@SerializedName("mobile_phone") val phone: String)