package ua.com.wl.dlp.data.api.requests.consumer.profile

import com.google.gson.annotations.SerializedName

data class DeleteProfileRequest(
    @SerializedName("code")
    val code: String
)